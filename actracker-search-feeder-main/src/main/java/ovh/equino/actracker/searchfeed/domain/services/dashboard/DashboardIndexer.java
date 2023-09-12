package ovh.equino.actracker.searchfeed.domain.services.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardIndex;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class DashboardIndexer extends EntityIndexer<DashboardId, Dashboard> {


    DashboardIndexer(DashboardStore dashboardStore, DashboardIndex dashboardIndex) {
        super(dashboardStore, dashboardIndex);
    }

    public void indexDashboard(Dashboard dashboardToIndex) {
        super.index(dashboardToIndex);
    }

}
