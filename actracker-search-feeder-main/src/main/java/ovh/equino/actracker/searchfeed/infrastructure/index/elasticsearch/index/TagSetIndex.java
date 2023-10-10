package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.DeleteAliasRequest;
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

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class TagSetIndex {

    private static final Logger LOG = LoggerFactory.getLogger(TagSetIndex.class);

    private static final String COMMON_MAPPINGS_DIR_PATH = "/elasticsearch/mappings";
    private static final String INDEX_NAME = "tagset";
    private static final String MAPPING_FILE_EXTENSION = ".json";

    private final String mappingsDirPath;
    private final ElasticsearchClient client;
    private final List<VersionedIndex> versionedIndices;

    public TagSetIndex(ElasticsearchClient client, String environment) {
        this.client = client;
        this.mappingsDirPath = "%s/%s".formatted(COMMON_MAPPINGS_DIR_PATH, INDEX_NAME);
        List<String> indexVersions = getIndexVersionsFromMappingsFiles();
        versionedIndices = indexVersions.stream()
                .map(version -> new VersionedIndex(COMMON_MAPPINGS_DIR_PATH, INDEX_NAME, version, environment, client))
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
            String message = "Could not find index mapping files in resource %s".formatted(MAPPING_FILE_EXTENSION);
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
//        recreateIndexAlias();
        LOG.info("Elasticsearch index {} created", INDEX_NAME);
    }

    private void refreshAlias(VersionedIndex versionedIndex) {
        String aliasedVersion = getAliasedVersionFromFile();
        try {
            if (aliasedVersion.equals(versionedIndex.version())) {
                recreateAlias(versionedIndex.indexName());
            } else {
                deleteAliasIfExists(versionedIndex.indexName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteAliasIfExists(String versionedIndex) throws IOException {
        if (aliasExists(versionedIndex)) {
            DeleteAliasRequest deleteAliasRequest = new DeleteAliasRequest.Builder()
                    .name(INDEX_NAME)
                    .index(versionedIndex)
                    .build();
            client.indices().deleteAlias(deleteAliasRequest);
            LOG.info("Elasticsearch alias {} deleted for index {}", INDEX_NAME, versionedIndex);
        }
    }

    private void recreateAlias(String versionedIndex) throws IOException {
        PutAliasRequest putAliasRequest = new PutAliasRequest.Builder()
                .name(INDEX_NAME)
                .index(versionedIndex)
                .build();
        client.indices().putAlias(putAliasRequest);
        LOG.info("Elasticsearch alias {} set for index {}", INDEX_NAME, versionedIndex);
    }

    private boolean aliasExists(String versionedIndex) throws IOException {
        ExistsAliasRequest aliasExistsRequest = new ExistsAliasRequest.Builder()
                .name(INDEX_NAME)
                .index(versionedIndex)
                .build();
        BooleanResponse aliasExistsResponse = client.indices().existsAlias(aliasExistsRequest);
        return aliasExistsResponse.value();
    }

    private String getAliasedVersionFromFile() {
        String aliasedVersion;
        String aliasFilePath = "%s/alias".formatted(mappingsDirPath);
        try (InputStream aliasFileContent = getClass().getResourceAsStream(aliasFilePath)) {
            aliasedVersion = new String(requireNonNull(aliasFileContent).readAllBytes());

        } catch (IOException e) {
            String message = "Cannot create alias %s".formatted(INDEX_NAME);
            throw new RuntimeException(message, e);
        }
        return aliasedVersion;
    }
}
