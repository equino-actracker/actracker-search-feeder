package ovh.equino.actracker.searchfeed.infrastructure.processed.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.activity.ActivityIndexer;

class DirectActivityProcessedNotifier implements ActivityProcessedNotifier {

    private final ActivityIndexer activityIndexer;

    DirectActivityProcessedNotifier(ActivityIndexer activityIndexer) {
        this.activityIndexer = activityIndexer;
    }

    @Override
    public void notifyProcessed(EntityProcessedNotification<ActivityId, Activity> entityProcessedNotification) {
        activityIndexer.indexActivity(entityProcessedNotification.entityId());
    }
}
