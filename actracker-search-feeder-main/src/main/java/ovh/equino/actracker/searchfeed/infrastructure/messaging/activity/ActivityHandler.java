package ovh.equino.actracker.searchfeed.infrastructure.messaging.activity;

import ovh.equino.actracker.domain.activity.ActivityChangedNotification;
import ovh.equino.actracker.searchfeed.application.activity.ActivityService;
import ovh.equino.actracker.searchfeed.domain.activity.Activity;

public class ActivityHandler {

    private final ActivityService activityService;
    private final ActivityMapper activityMapper = new ActivityMapper();

    ActivityHandler(ActivityService activityService) {
        this.activityService = activityService;
    }

    public void handleNotification(ActivityChangedNotification activityChangedNotification) {
        Activity activity = activityMapper.toActivity(activityChangedNotification);
        activityService.indexActivity(activity);
    }
}
