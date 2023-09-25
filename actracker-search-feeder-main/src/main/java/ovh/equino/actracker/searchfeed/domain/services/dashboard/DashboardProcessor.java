package ovh.equino.actracker.searchfeed.domain.services.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardStore;
import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentProcessed;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;

import static java.util.Collections.emptyList;

public final class DashboardProcessor extends EntityProcessor<DashboardId, Dashboard, DashboardProcessedNotifier> {

    private final DashboardProcessedNotifier dashboardProcessededNotifier;

    DashboardProcessor(DashboardStore dashboardStore, DashboardProcessedNotifier dashboardProcessededNotifier) {
        super(dashboardStore);
        this.dashboardProcessededNotifier = dashboardProcessededNotifier;
    }

    public void processDashboard(Dashboard dashboard) {
        super.processAndNotify(dashboard);
    }

    @Override
    protected DashboardProcessedNotifier entityProcessedNotifier() {
        return dashboardProcessededNotifier;
    }

    @Override
    protected Collection<ChildrenNotifierOfParentProcessed<DashboardId, ? extends EntityId>> childrenNotifiers() {
        return emptyList();
    }
}
