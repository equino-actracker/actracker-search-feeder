package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface ActivityRefreshedNotifier extends EntityRefreshedNotifier<ActivityId, Activity> {

    @Override
    default Class<Activity> supportedEntityType() {
        return Activity.class;
    }
}
