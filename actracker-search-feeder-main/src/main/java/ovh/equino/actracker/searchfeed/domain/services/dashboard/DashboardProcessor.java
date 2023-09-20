package ovh.equino.actracker.searchfeed.domain.services.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

class DashboardProcessor extends EntityProcessor<DashboardId, Dashboard, DashboardRefreshedNotifier> {

    private final DashboardRefreshedNotifier dashboardRefreshedNotifier;

    protected DashboardProcessor(DashboardStore dashboardStore, DashboardRefreshedNotifier dashboardRefreshedNotifier) {
        super(dashboardStore);
        this.dashboardRefreshedNotifier = dashboardRefreshedNotifier;
    }

    @Override
    protected void processAndNotify(Dashboard dashboard) {
        super.processAndNotify(dashboard);
    }

    @Override
    protected DashboardRefreshedNotifier entityRefreshedNotifier() {
        return dashboardRefreshedNotifier;
    }
}
