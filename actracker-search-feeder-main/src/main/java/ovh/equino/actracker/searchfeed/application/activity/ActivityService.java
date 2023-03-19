package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.activity.ActivityIndex;
import ovh.equino.actracker.searchfeed.domain.activity.ActivityStore;

import java.util.Optional;

public class ActivityService {

    private final ActivityIndex activityIndex;
    private final ActivityStore activityStore;

    ActivityService(ActivityIndex activityIndex, ActivityStore activityStore) {
        this.activityIndex = activityIndex;
        this.activityStore = activityStore;
    }

    void indexActivity(Activity activity) {
        ActivityId activityId = activity.id();
        Optional<Activity> storedActivity = activityStore.get(activityId);

        storedActivity.ifPresentOrElse(
                stored -> replaceIfApplicable(stored, activity),
                () -> activityStore.put(activityId, activity)
        );
    }

    private void replaceIfApplicable(Activity stored, Activity activity) {
        if (activity.shouldReplace(stored)) {
            activityStore.put(activity.id(), activity);
            activityIndex.index(activity);
        }
    }
}
