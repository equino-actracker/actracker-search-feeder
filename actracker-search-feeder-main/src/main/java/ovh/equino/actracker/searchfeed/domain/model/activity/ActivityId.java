package ovh.equino.actracker.searchfeed.domain.model.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public final class ActivityId extends EntityId {

    public ActivityId(@JsonProperty("id") UUID id) {
        super(id);
    }
}
