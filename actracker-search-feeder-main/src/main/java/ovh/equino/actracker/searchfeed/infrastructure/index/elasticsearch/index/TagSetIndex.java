package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;
import co.elastic.clients.elasticsearch.cat.indices.IndicesRecord;
import co.elastic.clients.elasticsearch.indices.DeleteAliasRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsAliasRequest;
import co.elastic.clients.elasticsearch.indices.PutAliasRequest;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class TagSetIndex {

    private static final Logger LOG = LoggerFactory.getLogger(TagSetIndex.class);

    private static final String COMMON_MAPPINGS_DIR_PATH = "/elasticsearch/mappings";
    private static final String INDEX_NAME = "tagset";
    private static final String MAPPING_FILE_EXTENSION = ".json";

    private final String mappingsDirPath;
    private final String indexAlias;
    private final ElasticsearchClient client;
    private final List<VersionedIndex> versionedIndices;

    public TagSetIndex(ElasticsearchClient client, String environment) {
        this.client = client;
        this.mappingsDirPath = "%s/%s".formatted(COMMON_MAPPINGS_DIR_PATH, INDEX_NAME);
        this.indexAlias = "%s_%s".formatted(INDEX_NAME, environment);
        List<String> indexVersions = getIndexVersionsFromMappingsFiles();
        versionedIndices = indexVersions.stream()
                .map(version -> new VersionedIndex(COMMON_MAPPINGS_DIR_PATH, indexAlias, version, client))
                .toList();
    }

    private List<String> getIndexVersionsFromMappingsFiles() {
        try {
            List<Path> mappingsPaths = getMappingsPaths();
            return mappingsPaths.stream()
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .map(path -> substringBefore(path, MAPPING_FILE_EXTENSION))
                    .toList();
        } catch (Exception e) {
            String message = "Could not find index mapping files in resource %s".formatted(mappingsDirPath);
            throw new RuntimeException(message, e);
        }
    }

    private List<Path> getMappingsPaths() throws IOException, URISyntaxException {
        URL mappingsDirUrl = getClass().getResource(mappingsDirPath);
        URI mappingsDirUri = requireNonNull(mappingsDirUrl).toURI();

        if ("jar".equals(mappingsDirUri.getScheme())) {
            return getMappingsPathsFromJar(mappingsDirUri);
        } else {
            return getMappingsPathsFromFilesystem(mappingsDirUri);
        }
    }

    private List<Path> getMappingsPathsFromJar(URI resourceUri) throws IOException {
        List<Path> mappingsPaths;
        try (FileSystem fileSystem = FileSystems.newFileSystem(resourceUri, Collections.emptyMap())) {
            Path resourcesPath = fileSystem.getPath("/");
            mappingsPaths = extractMappingsPaths(resourcesPath);
        }
        return mappingsPaths;
    }

    private List<Path> getMappingsPathsFromFilesystem(URI resourceUri) throws IOException {
        Path resourcesPath = Paths.get(resourceUri);
        return extractMappingsPaths(resourcesPath);
    }

    private List<Path> extractMappingsPaths(Path resourcesPath) throws IOException {
        return Files.walk(resourcesPath)
                .filter(Objects::nonNull)
                .filter(path -> path.toString().contains(mappingsDirPath))
                .filter(path -> path.toString().endsWith(MAPPING_FILE_EXTENSION))
                .toList();
    }

    public void create() {
        versionedIndices.forEach(VersionedIndex::create);
        versionedIndices.forEach(this::refreshAlias);
        removeDecommissionedIndices(versionedIndices);
        LOG.info("Elasticsearch index {} created", indexAlias);
    }

    private void refreshAlias(VersionedIndex versionedIndex) {
        String versionedIndexName = versionedIndex.indexName();
        try {
            String aliasedVersion = getAliasedVersionFromFile();
            if (aliasedVersion.equals(versionedIndex.version())) {
                recreateAlias(versionedIndexName);
            } else {
                deleteAliasIfExists(versionedIndexName);
            }
        } catch (IOException e) {
            String message = "Cannot recreate Elasticsearch alias %s for index %s"
                    .formatted(indexAlias, versionedIndexName);
            throw new RuntimeException(message, e);
        }
    }

    private void deleteAliasIfExists(String versionedIndex) throws IOException {
        if (aliasExists(versionedIndex)) {
            DeleteAliasRequest deleteAliasRequest = new DeleteAliasRequest.Builder()
                    .name(indexAlias)
                    .index(versionedIndex)
                    .build();
            client.indices().deleteAlias(deleteAliasRequest);
            LOG.info("Elasticsearch alias {} unset for index {}", indexAlias, versionedIndex);
        }
    }

    private void recreateAlias(String versionedIndex) throws IOException {
        PutAliasRequest putAliasRequest = new PutAliasRequest.Builder()
                .name(indexAlias)
                .index(versionedIndex)
                .build();
        client.indices().putAlias(putAliasRequest);
        LOG.info("Elasticsearch alias {} set for index {}", indexAlias, versionedIndex);
    }

    private boolean aliasExists(String versionedIndex) throws IOException {
        ExistsAliasRequest aliasExistsRequest = new ExistsAliasRequest.Builder()
                .name(indexAlias)
                .index(versionedIndex)
                .build();
        BooleanResponse aliasExistsResponse = client.indices().existsAlias(aliasExistsRequest);
        return aliasExistsResponse.value();
    }

    private String getAliasedVersionFromFile() throws IOException {
        String aliasFilePath = "%s/alias".formatted(mappingsDirPath);
        try (InputStream aliasFileContent = getClass().getResourceAsStream(aliasFilePath)) {
            return new String(requireNonNull(aliasFileContent).readAllBytes());
        }
    }

    private void removeDecommissionedIndices(List<VersionedIndex> versionedIndices) {
        Set<String> maintainedIndices = versionedIndices.stream()
                .map(VersionedIndex::indexName)
                .collect(toUnmodifiableSet());
        try {
            List<String> decommissionedIndices = fetchDecommissionedIndices(maintainedIndices);
            if (isEmpty(decommissionedIndices)) {
                LOG.info("No decommissioned indices to delete. Skipping.");
            } else {
                deleteIndices(decommissionedIndices);
                LOG.info("Decommissioned indices were deleted: {}", decommissionedIndices);
            }
        } catch (Exception e) {
            String message = "Unable to clean all decommissioned indices.";
            throw new RuntimeException(message, e);
        }
    }

    private List<String> fetchDecommissionedIndices(Set<String> maintainedIndices) throws IOException {
        IndicesResponse createdIndices = client.cat().indices();
        return createdIndices.valueBody().stream()
                .map(IndicesRecord::index)
                .filter(index -> startsWith(index, indexAlias))
                .filter(not(maintainedIndices::contains))
                .toList();
    }

    private void deleteIndices(List<String> indicesToDelete) throws IOException {
        DeleteIndexRequest deleteIndicesRequest = new DeleteIndexRequest.Builder()
                .index(indicesToDelete)
                .build();
        client.indices().delete(deleteIndicesRequest);
    }
}
