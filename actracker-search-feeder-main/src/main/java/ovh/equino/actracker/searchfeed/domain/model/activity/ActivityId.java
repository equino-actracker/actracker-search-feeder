package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public final class ActivityId extends EntityId {

    public ActivityId(UUID id) {
        super(id);
    }
}
