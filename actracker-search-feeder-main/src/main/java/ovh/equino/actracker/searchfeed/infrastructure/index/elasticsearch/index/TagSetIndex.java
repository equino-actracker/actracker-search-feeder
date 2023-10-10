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

public class TagSetIndex {

    private static final Logger LOG = LoggerFactory.getLogger(TagSetIndex.class);

    private static final String MAPPINGS_DIR_PATH = "/elasticsearch/mappings/tagset";
    private static final String INDEX_NAME = "tagset";

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
        URL mappingsDirUrl = getClass().getResource(MAPPINGS_DIR_PATH);
        URI mappingsDirUri = mappingsDirUrl.toURI();
        Path mappingsDirPath = getMappingDirPath(mappingsDirUri);

        List<String> versions = extractVersions(mappingsDirPath);
        System.out.println("Versions: " + versions);
    }

    private Path getMappingDirPath(URI resourceUri) throws IOException {
        Path resourcesPath;
        if ("jar".equals(resourceUri.getScheme())) {
            try (FileSystem fileSystem = FileSystems.newFileSystem(resourceUri, Collections.emptyMap())) {
                resourcesPath = fileSystem.getPath("/");
            }
        } else {
            resourcesPath = Paths.get(resourceUri);
        }
        return resourcesPath;
    }

    private List<String> extractVersions(Path resourcesPath) throws IOException {
        return Files.walk(resourcesPath)
                .filter(Objects::nonNull)
                .map(Path::toString)
                .filter(path -> path.contains(MAPPINGS_DIR_PATH))
                .filter(path -> path.endsWith(".json"))
                .toList();
    }
}
