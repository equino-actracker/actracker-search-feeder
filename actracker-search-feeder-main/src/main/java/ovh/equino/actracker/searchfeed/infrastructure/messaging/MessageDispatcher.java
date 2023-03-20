package ovh.equino.actracker.searchfeed.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ovh.equino.actracker.domain.activity.ActivityChangedNotification;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.activity.ActivityHandler;

public class MessageDispatcher {

    private final ActivityHandler activityHandler;
    private final ObjectMapper objectMapper;

    MessageDispatcher(ActivityHandler activityHandler) {
        this.activityHandler = activityHandler;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
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
