package ovh.equino.actracker.searchfeed.infrastructure.messaging.activity;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.activity.ActivityChangedNotification;
import ovh.equino.actracker.searchfeed.application.activity.ActivityService;
import ovh.equino.actracker.searchfeed.application.activity.IndexActivityCommand;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.NotificationHandler;

class ActivityHandler implements NotificationHandler<ActivityChangedNotification> {

    private final ActivityService activityService;

    ActivityHandler(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void handleNotification(Notification<?> activityChangedNotification) {

        Notification<ActivityChangedNotification> notification = (Notification<ActivityChangedNotification>) activityChangedNotification;

        IndexActivityCommand indexActivityCommand = new IndexActivityCommand(
                notification.id(),
                notification.data().activity().deleted(),
                notification.version(),
                notification.data().activity().creatorId(),
                notification.data().activity().title(),
                notification.data().activity().startTime(),
                notification.data().activity().endTime(),
                notification.data().activity().comment(),
                notification.data().activity().tags()
        );

        activityService.indexActivity(indexActivityCommand);
    }

    @Override
    public Class<ActivityChangedNotification> supportedNotificationType() {
        return ActivityChangedNotification.class;
    }
}
