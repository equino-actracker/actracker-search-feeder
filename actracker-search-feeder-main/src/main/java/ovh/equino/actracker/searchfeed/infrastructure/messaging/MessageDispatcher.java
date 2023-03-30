package ovh.equino.actracker.searchfeed.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.activity.ActivityChangedNotification;

import java.util.Set;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.util.Collections.singleton;

public class MessageDispatcher {

    private final NotificationHandler<ActivityChangedNotification> activityHandler;
    private final ObjectMapper objectMapper;

    MessageDispatcher(NotificationHandler<ActivityChangedNotification> activityHandler) {
        this.activityHandler = activityHandler;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void dispatchMessage(String rawMessage) {
        try {
            Notification<?> notification =
                    objectMapper.readValue(rawMessage, new TypeReference<Notification<ActivityChangedNotification>>() {});
            activityHandler.handleNotification(notification);
        } catch (JsonProcessingException e) {
            // TODO uncomment
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public Set<Class<?>> supportedNotificationTypes() {
        return singleton(activityHandler.supportedNotificationType());
    }
}
