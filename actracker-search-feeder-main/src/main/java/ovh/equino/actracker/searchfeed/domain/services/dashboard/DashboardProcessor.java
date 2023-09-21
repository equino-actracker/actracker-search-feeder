package ovh.equino.actracker.searchfeed.domain.services.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardStore;
import ovh.equino.actracker.searchfeed.domain.model.ChildrenNotifierOfParentRefresh;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;

import static java.util.Collections.emptyList;

public final class DashboardProcessor extends EntityProcessor<DashboardId, Dashboard, DashboardRefreshedNotifier> {

    private final DashboardRefreshedNotifier dashboardRefreshedNotifier;

    DashboardProcessor(DashboardStore dashboardStore, DashboardRefreshedNotifier dashboardRefreshedNotifier) {
        super(dashboardStore);
        this.dashboardRefreshedNotifier = dashboardRefreshedNotifier;
    }

    public void processDashboard(Dashboard dashboard) {
        super.processAndNotify(dashboard);
    }

    @Override
    protected DashboardRefreshedNotifier entityRefreshedNotifier() {
        return dashboardRefreshedNotifier;
    }

    @Override
    protected Collection<ChildrenNotifierOfParentRefresh<DashboardId, ? extends EntityId>> childrenNotifiers() {
        return emptyList();
    }
}
