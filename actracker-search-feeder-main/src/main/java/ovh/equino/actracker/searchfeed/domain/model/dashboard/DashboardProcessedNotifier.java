package ovh.equino.actracker.searchfeed.domain.model.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotifier;

public interface DashboardProcessedNotifier extends EntityProcessedNotifier<DashboardId, Dashboard> {

    @Override
    default Class<Dashboard> supportedEntityType() {
        return Dashboard.class;
    }
}
