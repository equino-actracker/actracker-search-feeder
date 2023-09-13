package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jooq.JSONB;
import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

class JooqEntityStore<ID extends EntityId, ENTITY extends Entity<ID>> {

    private final ObjectMapper objectMapper;

    protected JooqEntityStore() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setVisibility(FIELD, ANY);
        this.objectMapper.setVisibility(GETTER, NONE);
        this.objectMapper.setVisibility(IS_GETTER, NONE);
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    protected String serialize(ENTITY entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected ENTITY deserialize(JSONB json, Class<ENTITY> type) {
        try {
            return objectMapper.readValue(json.data(), type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
