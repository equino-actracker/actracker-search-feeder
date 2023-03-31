package ovh.equino.actracker.searchfeed.infrastructure.messaging;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.exception.ParseException;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MessageDispatcher {

    private final List<NotificationHandler<?>> notificationHandlers;

    MessageDispatcher(List<NotificationHandler<?>> notificationHandlers) {
        this.notificationHandlers = notificationHandlers;
    }

    public Set<Class<?>> supportedNotificationTypes() {
        return notificationHandlers.stream()
                .map(NotificationHandler::supportedNotificationType)
                .collect(toSet());
    }

    public void dispatchMessage(String rawMessage) {
        try {
            Notification<?> notification = Notification.fromJson(rawMessage);
            Class<?> notificationType = notification.notificationType();
            handlerForType(notificationType).handleNotification(notification);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private NotificationHandler<?> handlerForType(Class<?> notificationType) {
        return notificationHandlers.stream()
                .filter(handler -> handler.supportedNotificationType().equals(notificationType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Handler not found for notification of type %s".formatted(notificationType.getCanonicalName())
                ));
    }
}
