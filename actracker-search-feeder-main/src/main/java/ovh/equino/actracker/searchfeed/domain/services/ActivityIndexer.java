package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;

import java.util.Optional;

public class ActivityIndexer {

    private final ActivityStore activityStore;
    private final ActivityIndex activityIndex;

    ActivityIndexer(ActivityStore activityStore, ActivityIndex activityIndex) {
        this.activityStore = activityStore;
        this.activityIndex = activityIndex;
    }

    public void indexActivity(Activity activity) {
        ActivityId activityId = activity.id();
        Optional<Activity> storedActivity = activityStore.get(activityId);

        storedActivity.ifPresentOrElse(
                (stored) -> reindexIfOutdated(stored, activity),
                () -> indexOrDelete(activity)
        );
    }

    private void reindexIfOutdated(Activity stored, Activity activity) {
        if (activity.shouldReplace(stored)) {
            indexOrDelete(activity);
        }
    }

    private void index(Activity activity) {
        activityStore.put(activity.id(), activity);
        activityIndex.index(activity);
    }

    private void indexOrDelete(Activity activity) {
        if (activity.saveDeleted()) {
            activityIndex.delete(activity.id());
        } else {
            activityIndex.index(activity);
        }
    }
}
