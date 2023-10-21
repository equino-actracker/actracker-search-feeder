package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardGraph;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardIndex;

import java.util.Collection;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class ElasticDashboardIndex extends ElasticIndex implements DashboardIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticDashboardIndex.class);
    private static final String INDEX_NAME = "dashboard";

    public ElasticDashboardIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(DashboardGraph dashboardGraph) {
        ElasticDashboardDocument document = new ElasticDashboardDocument(
                dashboardGraph.entityId().toString(),
                dashboardGraph.dashboard().creatorId().toString(),
                dashboardGraph.dashboard().name(),
                toGranteeIds(dashboardGraph.dashboard())
        );
        super.indexDocument(document);
        LOG.info("Dashboard document with ID={} successfully indexed to Elasticsearch.", dashboardGraph.entityId().id());
    }

    @Override
    public void delete(DashboardId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("Dashboard document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private Collection<String> toGranteeIds(Dashboard dashboard) {
        if (isEmpty(dashboard.grantees())) {
            return null;
        }
        return dashboard.grantees()
                .stream()
                .map(EntityId::toString)
                .collect(toSet());
    }

    private record ElasticDashboardDocument(String id,
                                            String creator_id,
                                            String name,
                                            Collection<String> grantees)
            implements ElasticDocument {
    }
}
