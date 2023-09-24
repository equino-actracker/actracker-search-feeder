package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotificationHandler;

public interface ActivityRefreshedNotificationHandler extends EntityRefreshedNotificationHandler<ActivityId> {

    @Override
    default Class<ActivityId> supportedIdType() {
        return ActivityId.class;
    }
}
