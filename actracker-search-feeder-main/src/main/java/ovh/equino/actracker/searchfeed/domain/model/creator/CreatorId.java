package ovh.equino.actracker.searchfeed.domain.model.creator;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public final class CreatorId extends EntityId {
    public CreatorId(@JsonProperty("id") UUID id) {
        super(id);
    }
}
