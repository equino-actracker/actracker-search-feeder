package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagGraph;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagIndex;

import java.util.Collection;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class ElasticTagIndex extends ElasticIndex implements TagIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticTagIndex.class);
    private static final String INDEX_NAME = "tag";

    public ElasticTagIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(TagGraph tagGraph) {
        ElasticTagDocument document = new ElasticTagDocument(
                tagGraph.entityId().toString(),
                tagGraph.tag().creatorId().toString(),
                now().toEpochMilli(),
                tagGraph.tag().name(),
                toGranteeIds(tagGraph.tag())
        );
        super.indexDocument(document);
        LOG.info("Tag document with ID={} successfully indexed to Elasticsearch.", tagGraph.entityId().id());
    }

    @Override
    public void delete(TagId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("Tag document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private Collection<String> toGranteeIds(Tag tag) {
        if (isEmpty(tag.grantees())) {
            return null;
        }
        return tag.grantees()
                .stream()
                .map(EntityId::toString)
                .collect(toSet());
    }

    private record ElasticTagDocument(String id,
                                      String creator_id,
                                      Long indexing_time,
                                      String name,
                                      Collection<String> grantees)
            implements ElasticDocument {
    }
}
