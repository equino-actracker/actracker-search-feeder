package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.dashboard.DashboardIndexer;

class DirectDashboardRefreshedNotifier implements DashboardRefreshedNotifier {

    private final DashboardIndexer dashboardIndexer;

    DirectDashboardRefreshedNotifier(DashboardIndexer dashboardIndexer) {
        this.dashboardIndexer = dashboardIndexer;
    }

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<DashboardId, Dashboard> entityRefreshedNotification) {
        dashboardIndexer.indexDashboard(entityRefreshedNotification.entityId());
    }
}
