package ovh.equino.actracker.searchfeed.domain.model.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface DashboardRefreshedNotifier extends EntityRefreshedNotifier<DashboardId, Dashboard> {

    @Override
    default Class<Dashboard> supportedEntityType() {
        return Dashboard.class;
    }
}
