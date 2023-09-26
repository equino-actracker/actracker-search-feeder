package ovh.equino.actracker.searchfeed.infrastructure.processed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.*;

public class EntityProcessedNotificationSerializer {

    private final ObjectMapper objectMapper;

    public EntityProcessedNotificationSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setVisibility(FIELD, ANY);
        this.objectMapper.setVisibility(GETTER, NONE);
        this.objectMapper.setVisibility(IS_GETTER, NONE);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String serialize(EntityProcessedNotification<?, ?> notification) {
        try {
            return objectMapper.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            String errorMessage = "Could not deserialize processed notification message for entity %s with ID=%s"
                    .formatted(
                            notification.entityType().getSimpleName(),
                            notification.entityId().toString()
                    );
            throw new RuntimeException(errorMessage, e);
        }
    }

    public EntityProcessedNotification<?, ?> deserialize(String message) {
        try {
            JavaType entityType = getEntityType(message);
            return objectMapper.readValue(message, entityType);
        } catch (JsonProcessingException e) {
            String errorMessage = "Could not deserialize notification message: %s".formatted(message);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private JavaType getEntityType(String rawMessage) throws JsonProcessingException {
        MessageTypes notification = objectMapper.readValue(rawMessage, MessageTypes.class);
        return objectMapper.getTypeFactory().constructParametricType(
                EntityProcessedNotification.class,
                notification.identifierType(),
                notification.entityType()
        );
    }

    private record MessageTypes(Class<?> identifierType, Class<?> entityType) {
    }
}
