package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;

import java.util.Optional;

public class ActivityIndexer {

    private final ActivityStore activityStore;
    private final ActivityIndex indexer;

    ActivityIndexer(ActivityStore activityStore, ActivityIndex activityIndex) {
        this.activityStore = activityStore;
        this.indexer = activityIndex;
    }

    public void indexActivity(Activity activityToIndex) {
        ActivityId activityId = activityToIndex.id();
        Optional<Activity> storedActivity = activityStore.get(activityId);

        storedActivity.ifPresentOrElse(
                (stored) -> saveOrDeleteIfOverrides(stored, activityToIndex),
                () -> indexIfNotDeleted(activityToIndex)
        );
    }

    private void indexIfNotDeleted(Activity activity) {
        activityStore.put(activity.id(), activity);
        if (activity.isNotDeleted()) {
            indexer.index(activity);
        }
    }

    private void saveOrDeleteIfOverrides(Activity storedActivity, Activity newActivity) {
        if (newActivity.shouldReplace(storedActivity)) {
            reindexOrDelete(newActivity);
        }
    }

    private void reindexOrDelete(Activity activity) {
        ActivityId activityId = activity.id();
        activityStore.put(activityId, activity);
        if (activity.isNotDeleted()) {
            indexer.index(activity);
        } else {
            indexer.delete(activityId);
        }
    }

}
