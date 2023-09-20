package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

class ActivityProcessor extends EntityProcessor<ActivityId, Activity, ActivityRefreshedNotifier> {

    private final ActivityRefreshedNotifier activityRefreshedNotifier;

    protected ActivityProcessor(ActivityStore activityStore, ActivityRefreshedNotifier activityRefreshedNotifier) {
        super(activityStore);
        this.activityRefreshedNotifier = activityRefreshedNotifier;
    }

    @Override
    protected void processAndNotify(Activity activity) {
        super.processAndNotify(activity);
    }

    @Override
    protected ActivityRefreshedNotifier entityRefreshedNotifier() {
        return activityRefreshedNotifier;
    }
}
