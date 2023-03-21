package ovh.equino.actracker.searchfeed.infrastructure.messaging.activity;

import ovh.equino.actracker.domain.activity.ActivityChangedNotification;
import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;

class ActivityMapper {

    Activity toActivity(ActivityChangedNotification notification) {
        return new Activity(
                new ActivityId(notification.id()),
                new Version(notification.version()),
                notification.activity().startTime(),
                notification.activity().endTime()
        );
    }
}
