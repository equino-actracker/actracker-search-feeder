package ovh.equino.actracker.searchfeed.infrastructure.messaging.activity;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.activity.ActivityChangedNotification;
import ovh.equino.actracker.domain.activity.MetricValue;
import ovh.equino.actracker.searchfeed.application.activity.ActivityService;
import ovh.equino.actracker.searchfeed.application.activity.IndexActivityCommand;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.NotificationHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
                notification.data().activity().title(),
                notification.data().activity().startTime(),
                notification.data().activity().endTime(),
                notification.data().activity().comment(),
                notification.data().activity().tags(),
                toMetricValueMap(notification.data().activity().metricValues())
        );

        activityService.indexActivity(indexActivityCommand);
    }

    @Override
    public Class<ActivityChangedNotification> supportedNotificationType() {
        return ActivityChangedNotification.class;
    }

    private Map<UUID, BigDecimal> toMetricValueMap(Collection<MetricValue> metricValues) {
        return requireNonNullElse(metricValues, new ArrayList<MetricValue>())
                .stream()
                .collect(Collectors.toMap(
                        MetricValue::metricId,
                        MetricValue::value
                ));
    }
}
