package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentRefresh;

class ActivityNotifierOnTagRefresh implements ChildrenNotifierOfParentRefresh<TagId, ActivityId> {

    private final ActivityStore activityStore;
    private final ActivityRefreshedNotifier activityRefreshedNotifier;

    ActivityNotifierOnTagRefresh(ActivityStore activityStore, ActivityRefreshedNotifier activityRefreshedNotifier) {
        this.activityStore = activityStore;
        this.activityRefreshedNotifier = activityRefreshedNotifier;
    }

    @Override
    public void notifyParentChanged(TagId activityId) {
        activityStore.findByTag(activityId)
                .stream()
                .map(id -> new EntityRefreshedNotification<>(id, Activity.class))
                .forEach(activityRefreshedNotifier::notifyRefreshed);
    }
}
