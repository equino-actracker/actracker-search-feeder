package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class ActivityIndexer extends EntityIndexer<ActivityId, Activity> {


    ActivityIndexer(ActivityStore activityStore, ActivityIndex activityIndex) {
        super(activityStore, activityIndex);
    }

    public void indexActivity(Activity activityToIndex) {
        super.index(activityToIndex);
    }

}
