package ovh.equino.actracker.searchfeed.domain.services.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.dashboard.*;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class DashboardIndexer extends EntityIndexer<DashboardId, Dashboard, DashboardGraph> {


    DashboardIndexer(DashboardStore dashboardStore, DashboardIndex dashboardIndex) {
        super(dashboardStore, dashboardIndex);
    }

    @Override
    protected DashboardGraph buildEntityGraph(Dashboard dashboard) {
        return new DashboardGraph(dashboard);
    }

    @Override
    public Class<Dashboard> supportedEntityType() {
        return Dashboard.class;
    }
}
