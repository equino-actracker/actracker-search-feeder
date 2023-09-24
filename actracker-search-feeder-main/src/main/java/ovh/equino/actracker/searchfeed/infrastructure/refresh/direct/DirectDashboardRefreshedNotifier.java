package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardRefreshedNotifier;

class DirectDashboardRefreshedNotifier implements DashboardRefreshedNotifier {

    private final DirectDashboardRefreshedNotificationHandler notificationHandler;

    DirectDashboardRefreshedNotifier(DirectDashboardRefreshedNotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void notifyRefreshed(DashboardId dashboardId) {
        notificationHandler.refreshedNotificationReceived(dashboardId);
    }
}
