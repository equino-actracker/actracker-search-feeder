package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;

class DirectActivityRefreshedNotifier implements ActivityRefreshedNotifier {

    private final DirectActivityRefreshedNotificationHandler notificationHandler;

    DirectActivityRefreshedNotifier(DirectActivityRefreshedNotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void notifyRefreshed(ActivityId activityId) {
        notificationHandler.refreshedNotificationReceived(activityId);
    }
}
