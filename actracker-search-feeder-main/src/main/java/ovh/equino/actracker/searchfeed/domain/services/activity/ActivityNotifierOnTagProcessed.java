package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentProcessed;

class ActivityNotifierOnTagProcessed implements ChildrenNotifierOfParentProcessed<TagId> {

    private final ActivityStore activityStore;
    private final ActivityProcessedNotifier activityProcessedNotifier;

    ActivityNotifierOnTagProcessed(ActivityStore activityStore, ActivityProcessedNotifier activityProcessedNotifier) {
        this.activityStore = activityStore;
        this.activityProcessedNotifier = activityProcessedNotifier;
    }

    @Override
    public void notifyParentChanged(TagId activityId) {
        activityStore.findByTag(activityId)
                .stream()
                .map(id -> new EntityProcessedNotification<>(id, Activity.class))
                .forEach(activityProcessedNotifier::notifyProcessed);
    }
}
