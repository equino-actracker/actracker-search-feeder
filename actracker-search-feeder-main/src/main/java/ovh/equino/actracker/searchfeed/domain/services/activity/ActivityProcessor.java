package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentProcessed;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;

import static java.util.Collections.emptyList;

public final class ActivityProcessor extends EntityProcessor<ActivityId, Activity, ActivityProcessedNotifier> {

    private final ActivityProcessedNotifier activityProcessedNotifier;

    ActivityProcessor(ActivityStore activityStore, ActivityProcessedNotifier activityProcessedNotifier) {
        super(activityStore);
        this.activityProcessedNotifier = activityProcessedNotifier;
    }

    public void processActivity(Activity activity) {
        super.processAndNotify(activity);
    }

    @Override
    protected ActivityProcessedNotifier entityProcessedNotifier() {
        return activityProcessedNotifier;
    }

    @Override
    protected Collection<ChildrenNotifierOfParentProcessed<ActivityId>> childrenNotifiers() {
        return emptyList();
    }
}
