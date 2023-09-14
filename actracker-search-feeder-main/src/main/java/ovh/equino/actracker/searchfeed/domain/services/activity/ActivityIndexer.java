package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.activity.*;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class ActivityIndexer extends EntityIndexer<ActivityId, Activity, ActivityGraph> {


    ActivityIndexer(ActivityStore activityStore, ActivityIndex activityIndex) {
        super(activityStore, activityIndex);
    }

    public void indexActivity(Activity activityToIndex) {
        super.index(activityToIndex);
    }

    @Override
    protected ActivityGraph buildEntityGraph(Activity activity) {
        return new ActivityGraph(activity);
    }
}
