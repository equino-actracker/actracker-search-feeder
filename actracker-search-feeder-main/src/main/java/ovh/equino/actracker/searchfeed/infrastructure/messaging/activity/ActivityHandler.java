package ovh.equino.actracker.searchfeed.infrastructure.messaging.activity;

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
    public void handleNotification(ActivityChangedNotification activityChangedNotification) {

        IndexActivityCommand indexActivityCommand = new IndexActivityCommand(
                activityChangedNotification.id(),
                activityChangedNotification.activity().deleted(),
                activityChangedNotification.version(),
                activityChangedNotification.activity().startTime(),
                activityChangedNotification.activity().endTime()
        );

        activityService.indexActivity(indexActivityCommand);
    }
}
