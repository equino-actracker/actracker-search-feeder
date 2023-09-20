package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

public final class ActivityProcessor extends EntityProcessor<ActivityId, Activity, ActivityRefreshedNotifier> {

    private final ActivityRefreshedNotifier activityRefreshedNotifier;

    ActivityProcessor(ActivityStore activityStore, ActivityRefreshedNotifier activityRefreshedNotifier) {
        super(activityStore);
        this.activityRefreshedNotifier = activityRefreshedNotifier;
    }

    public void processActivity(Activity activity) {
        super.processAndNotify(activity);
    }

    @Override
    protected ActivityRefreshedNotifier entityRefreshedNotifier() {
        return activityRefreshedNotifier;
    }
}
