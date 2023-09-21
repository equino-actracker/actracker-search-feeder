package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.ChildrenNotifierOfParentRefresh;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;

class ActivityNotifierOnTagRefresh implements ChildrenNotifierOfParentRefresh<TagId, ActivityId> {

    private final ActivityStore activityStore;
    private final ActivityRefreshedNotifier activityRefreshedNotifier;

    ActivityNotifierOnTagRefresh(ActivityStore activityStore, ActivityRefreshedNotifier activityRefreshedNotifier) {
        this.activityStore = activityStore;
        this.activityRefreshedNotifier = activityRefreshedNotifier;
    }

    @Override
    public void notifyParentChanged(TagId tagId) {
        System.out.printf("Updating activities containing tag with ID=%s%n", tagId.toString());
    }
}
