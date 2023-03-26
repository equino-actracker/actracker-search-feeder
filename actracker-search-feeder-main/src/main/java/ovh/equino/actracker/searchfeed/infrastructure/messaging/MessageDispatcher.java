package ovh.equino.actracker.searchfeed.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ovh.equino.actracker.domain.activity.ActivityChangedNotification;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

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
            ActivityChangedNotification activityChangedNotification =
                    objectMapper.readValue(rawMessage, ActivityChangedNotification.class);
            activityHandler.handleNotification(activityChangedNotification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
