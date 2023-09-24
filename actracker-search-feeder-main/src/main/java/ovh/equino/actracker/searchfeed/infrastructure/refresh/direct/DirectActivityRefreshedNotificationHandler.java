package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotificationHandler;
import ovh.equino.actracker.searchfeed.domain.services.activity.ActivityIndexer;

class DirectActivityRefreshedNotificationHandler implements ActivityRefreshedNotificationHandler {

    private final ActivityIndexer activityIndexer;

    DirectActivityRefreshedNotificationHandler(ActivityIndexer activityIndexer) {
        this.activityIndexer = activityIndexer;
    }

    @Override
    public void refreshedNotificationReceived(ActivityId activityId) {
        activityIndexer.indexActivity(activityId);
    }
}
