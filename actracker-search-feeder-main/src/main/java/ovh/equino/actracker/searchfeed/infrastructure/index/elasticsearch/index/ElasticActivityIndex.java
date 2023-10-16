package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityGraph;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;

public class ElasticActivityIndex extends ElasticIndex implements ActivityIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticActivityIndex.class);
    private static final String INDEX_NAME = "activity";

    public ElasticActivityIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(ActivityGraph entityGraph) {
        ElasticActivityDocument document = new ElasticActivityDocument(entityGraph.entityId().id().toString());
        super.indexDocument(document);
        LOG.info("Activity document with ID={} successfully indexed to Elasticsearch.", entityGraph.entityId().id());
    }

    @Override
    public void delete(ActivityId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("Activity document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private record ElasticActivityDocument(String id) implements ElasticDocument {
    }
}
