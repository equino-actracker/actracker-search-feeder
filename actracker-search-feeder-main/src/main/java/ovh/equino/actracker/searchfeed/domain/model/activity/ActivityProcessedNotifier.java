package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotifier;

public interface ActivityProcessedNotifier extends EntityProcessedNotifier<ActivityId, Activity> {

    @Override
    default Class<Activity> supportedEntityType() {
        return Activity.class;
    }
}
