package ovh.equino.actracker.searchfeed.infrastructure.messaging;

import ovh.equino.actracker.domain.Notification;

public interface NotificationHandler<T> {

    void handleNotification(Notification<?> notification);

    Class<T> supportedNotificationType();
}
