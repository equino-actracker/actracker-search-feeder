package ovh.equino.actracker.searchfeed.infrastructure.notifications.activity;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.activity.ActivityChangedNotification;
import ovh.equino.actracker.searchfeed.application.activity.ActivityService;
import ovh.equino.actracker.searchfeed.application.activity.IndexActivityCommand;
import ovh.equino.actracker.searchfeed.domain.model.metric.MetricId;
import ovh.equino.actracker.searchfeed.domain.model.metricValue.MetricValue;
import ovh.equino.actracker.searchfeed.infrastructure.notifications.NotificationHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.requireNonNullElse;

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
                notification.data().activity().tags(),
                toMetricValues(notification.data().activity().metricValues())
        );

        activityService.indexActivity(indexActivityCommand);
    }

    private Collection<MetricValue> toMetricValues(List<ovh.equino.actracker.domain.activity.MetricValue> metricValues) {
        return requireNonNullElse(metricValues, new ArrayList<ovh.equino.actracker.domain.activity.MetricValue>())
                .stream()
                .map(metricValue ->
                        new MetricValue(
                                new MetricId(metricValue.metricId()),
                                metricValue.value()
                        )
                )
                .toList();
    }


    @Override
    public Class<ActivityChangedNotification> supportedNotificationType() {
        return ActivityChangedNotification.class;
    }
}
