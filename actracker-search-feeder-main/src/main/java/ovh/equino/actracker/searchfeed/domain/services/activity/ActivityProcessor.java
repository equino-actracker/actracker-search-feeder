package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.model.ChildrenNotifierOfParentRefresh;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;

import static java.util.Collections.emptyList;

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

    @Override
    protected Collection<ChildrenNotifierOfParentRefresh<ActivityId, ? extends EntityId>> childrenNotifiers() {
        return emptyList();
    }
}
