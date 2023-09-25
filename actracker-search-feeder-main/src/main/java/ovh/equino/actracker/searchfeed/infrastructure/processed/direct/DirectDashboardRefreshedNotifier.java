package ovh.equino.actracker.searchfeed.infrastructure.processed.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.dashboard.DashboardIndexer;

class DirectDashboardRefreshedNotifier implements DashboardProcessedNotifier {

    private final DashboardIndexer dashboardIndexer;

    DirectDashboardRefreshedNotifier(DashboardIndexer dashboardIndexer) {
        this.dashboardIndexer = dashboardIndexer;
    }

    @Override
    public void notifyProcessed(EntityProcessedNotification<DashboardId, Dashboard> entityProcessedNotification) {
        dashboardIndexer.indexDashboard(entityProcessedNotification.entityId());
    }
}
