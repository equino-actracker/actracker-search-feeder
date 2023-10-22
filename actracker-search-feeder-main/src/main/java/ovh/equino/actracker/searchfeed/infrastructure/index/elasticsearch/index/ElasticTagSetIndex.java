package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetGraph;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetIndex;

import java.util.Collection;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class ElasticTagSetIndex extends ElasticIndex implements TagSetIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticTagSetIndex.class);
    private static final String INDEX_NAME = "tagset";

    public ElasticTagSetIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(TagSetGraph tagSetGraph) {
        ElasticTagSetDocument document = new ElasticTagSetDocument(
                tagSetGraph.entityId().toString(),
                tagSetGraph.tagSet().creatorId().toString(),
                now().toEpochMilli(),
                tagSetGraph.tagSet().name(),
                toTagIds(tagSetGraph.tags())
        );
        super.indexDocument(document);
        LOG.info("TagSet document with ID={} successfully indexed to Elasticsearch.", tagSetGraph.entityId().id());
    }

    @Override
    public void delete(TagSetId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("TagSet document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private Collection<String> toTagIds(Collection<Tag> tags) {
        if (isEmpty(tags)) {
            return null;
        }
        return tags
                .stream()
                .map(tag -> tag.id().toString())
                .collect(toSet());
    }

    private record ElasticTagSetDocument(String id,
                                         String creator_id,
                                         Long indexing_time,
                                         String name,
                                         Collection<String> tags)
            implements ElasticDocument {
    }
}
