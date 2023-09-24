package ovh.equino.actracker.searchfeed.domain.model.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotificationHandler;

public interface DashboardRefreshedNotificationHandler extends EntityRefreshedNotificationHandler<DashboardId> {

    @Override
    default Class<DashboardId> supportedIdType() {
        return DashboardId.class;
    }
}
