package ovh.equino.actracker.searchfeed.domain.model.tagset;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public class TagSetId extends EntityId {


    public TagSetId(@JsonProperty("id") UUID id) {
        super(id);
    }
}
