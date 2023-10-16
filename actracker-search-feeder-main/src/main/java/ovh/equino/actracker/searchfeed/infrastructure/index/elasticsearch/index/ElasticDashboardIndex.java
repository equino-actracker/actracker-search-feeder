package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardGraph;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardIndex;

public class ElasticDashboardIndex extends ElasticIndex implements DashboardIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticDashboardIndex.class);
    private static final String INDEX_NAME = "dashboard";

    public ElasticDashboardIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(DashboardGraph entityGraph) {
        ElasticDashboardDocument document = new ElasticDashboardDocument(entityGraph.entityId().id().toString());
        super.indexDocument(document);
        LOG.info("Dashboard document with ID={} successfully indexed to Elasticsearch.", entityGraph.entityId().id());
    }

    @Override
    public void delete(DashboardId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("Dashboard document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private record ElasticDashboardDocument(String id) implements ElasticDocument {
    }
}
