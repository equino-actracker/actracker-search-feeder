package ovh.equino.actracker.searchfeed.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ovh.equino.actracker.domain.Notification;

import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.util.stream.Collectors.toSet;

public class MessageDispatcher {

    private final List<NotificationHandler<?>> notificationHandlers;
    private final ObjectMapper objectMapper;

    MessageDispatcher(List<NotificationHandler<?>> notificationHandlers) {
        this.notificationHandlers = notificationHandlers;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Set<Class<?>> supportedNotificationTypes() {
        return notificationHandlers.stream()
                .map(NotificationHandler::supportedNotificationType)
                .collect(toSet());
    }

    public void dispatchMessage(String rawMessage) {
        try {
            Class<?> notificationType = getNotificationType(rawMessage);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(
                    Notification.class,
                    notificationType
            );

            Notification<?> notification = objectMapper.readValue(rawMessage, javaType);
            handlerForType(notificationType).handleNotification(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> getNotificationType(String rawMessage) throws JsonProcessingException {
        Notification<?> notification = objectMapper.readValue(rawMessage, Notification.class);
        return notification.notificationType();
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
