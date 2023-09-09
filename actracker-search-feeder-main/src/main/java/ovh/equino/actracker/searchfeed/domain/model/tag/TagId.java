package ovh.equino.actracker.searchfeed.domain.model.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public final class TagId extends EntityId {

    public TagId(@JsonProperty("id") UUID id) {
        super(id);
    }
}
