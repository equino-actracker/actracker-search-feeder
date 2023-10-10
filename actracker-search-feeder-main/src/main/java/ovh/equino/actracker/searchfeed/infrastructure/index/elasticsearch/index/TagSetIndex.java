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

    private static final String MAPPINGS_DIR_PATH = "/elasticsearch/mappings/tagset";
    private static final String INDEX_NAME = "tagset";
    private static final String MAPPING_FILE_EXTENSION = ".json";

    private final ElasticsearchClient client;
    private final String environment;

    public TagSetIndex(ElasticsearchClient client, String environment) {
        this.client = client;
        this.environment = environment;
    }

    public void create() {
        try {
            getAllFilesInDir();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllFilesInDir() throws IOException, URISyntaxException {
        List<String> indexVersions = getVersions();

        System.out.println("Versions: " + indexVersions);
    }

    private List<String> getVersions() throws IOException, URISyntaxException {
        List<Path> mappingsPaths = getMappingsPaths();
        return mappingsPaths.stream()
                .map(Path::getFileName)
                .map(Path::toString)
                .map(path -> substringBefore(path, MAPPING_FILE_EXTENSION))
                .toList();
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
        List<Path> mappingsPaths;
        Path resourcesPath = Paths.get(resourceUri);
        extractMappingsPaths(resourcesPath);
        mappingsPaths = extractMappingsPaths(resourcesPath);
        return mappingsPaths;
    }

    private List<Path> extractMappingsPaths(Path resourcesPath) throws IOException {
        return Files.walk(resourcesPath)
                .filter(Objects::nonNull)
                .filter(path -> path.toString().contains(MAPPINGS_DIR_PATH))
                .filter(path -> path.toString().endsWith(MAPPING_FILE_EXTENSION))
                .toList();
    }
}
