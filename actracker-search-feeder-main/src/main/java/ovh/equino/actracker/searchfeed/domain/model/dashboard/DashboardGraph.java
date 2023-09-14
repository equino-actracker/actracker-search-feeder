package ovh.equino.actracker.searchfeed.domain.model.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;

public record DashboardGraph(Dashboard dashboard) implements EntityGraph<DashboardId> {

    @Override
    public DashboardId entityId() {
        return dashboard.id();
    }
}
