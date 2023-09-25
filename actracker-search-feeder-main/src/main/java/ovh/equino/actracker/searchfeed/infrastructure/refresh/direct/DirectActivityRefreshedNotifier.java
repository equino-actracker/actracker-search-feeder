package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.activity.ActivityIndexer;

class DirectActivityRefreshedNotifier implements ActivityRefreshedNotifier {

    private final ActivityIndexer activityIndexer;

    DirectActivityRefreshedNotifier(ActivityIndexer activityIndexer) {
        this.activityIndexer = activityIndexer;
    }

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<ActivityId, Activity> entityRefreshedNotification) {
        activityIndexer.indexActivity(entityRefreshedNotification.entityId());
    }
}
