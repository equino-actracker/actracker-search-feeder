package ovh.equino.actracker.searchfeed.domain.model.share;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public final class GranteeId extends EntityId {
    public GranteeId(@JsonProperty("id") UUID id) {
        super(id);
    }
}
