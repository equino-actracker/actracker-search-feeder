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
        LOG.info("Indexing activity graph with ID={} to Elasticsearch", entityGraph.entityId().id());
    }

    @Override
    public void delete(ActivityId id) {
        LOG.info("Deleting activity graph with ID={} from Elasticsearch", id.id());
    }
}
