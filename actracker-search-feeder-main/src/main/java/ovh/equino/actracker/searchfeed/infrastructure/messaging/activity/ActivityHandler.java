package ovh.equino.actracker.searchfeed.infrastructure.messaging.activity;

import ovh.equino.actracker.domain.activity.ActivityChangedNotification;
import ovh.equino.actracker.searchfeed.application.ActivityService;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.NotificationHandler;

class ActivityHandler implements NotificationHandler<ActivityChangedNotification> {

    private final ActivityService activityService; // [MC] Should infrastructure module depend on application? Probably
    private final ActivityMapper activityMapper = new ActivityMapper();

    ActivityHandler(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void handleNotification(ActivityChangedNotification activityChangedNotification) {
        Activity activity = activityMapper.toActivity(activityChangedNotification);
        activityService.indexActivity(activity);
    }
}
