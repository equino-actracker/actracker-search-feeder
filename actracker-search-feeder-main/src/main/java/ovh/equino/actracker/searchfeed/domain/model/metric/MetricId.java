package ovh.equino.actracker.searchfeed.domain.model.metric;

import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public final class MetricId extends EntityId {
    public MetricId(UUID id) {
        super(id);
    }
}
