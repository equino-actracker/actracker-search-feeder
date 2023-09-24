package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardRefreshedNotificationHandler;
import ovh.equino.actracker.searchfeed.domain.services.dashboard.DashboardIndexer;

class DirectDashboardRefreshedNotificationHandler implements DashboardRefreshedNotificationHandler {

    private final DashboardIndexer dashboardIndexer;

    DirectDashboardRefreshedNotificationHandler(DashboardIndexer dashboardIndexer) {
        this.dashboardIndexer = dashboardIndexer;
    }

    @Override
    public void refreshedNotificationReceived(DashboardId dashboardId) {
        dashboardIndexer.indexDashboard(dashboardId);
    }
}
