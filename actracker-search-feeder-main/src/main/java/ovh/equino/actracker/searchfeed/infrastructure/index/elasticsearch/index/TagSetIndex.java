package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    private static final String MAPPINGS_DIR_PATH = "/elasticsearch/mappings/tagset";
    private static final String INDEX_NAME = "tagset";
    private static final String MAPPING_FILE_EXTENSION = ".json";

    private final ElasticsearchClient client;
    private final List<VersionedIndex> versionedIndices;

    public TagSetIndex(ElasticsearchClient client, String environment) {
        this.client = client;
        List<String> indexVersions = getIndexVersionsFromMappingsFiles();
        versionedIndices = indexVersions.stream()
                .map(version -> new VersionedIndex(COMMON_MAPPINGS_DIR_PATH, INDEX_NAME, version, environment, client))
                .toList();
    }

    public void create() {
        versionedIndices.forEach(VersionedIndex::create);
        LOG.info("Elasticsearch index {} created", INDEX_NAME);
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
        URL mappingsDirUrl = getClass().getResource(MAPPINGS_DIR_PATH);
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
                .filter(path -> path.toString().contains(MAPPINGS_DIR_PATH))
                .filter(path -> path.toString().endsWith(MAPPING_FILE_EXTENSION))
                .toList();
    }
}
