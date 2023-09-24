package ovh.equino.actracker.searchfeed.domain.model.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface DashboardRefreshedNotifier extends EntityRefreshedNotifier<DashboardId> {

    @Override
    default Class<DashboardId> supportedIdType() {
        return DashboardId.class;
    }
}
