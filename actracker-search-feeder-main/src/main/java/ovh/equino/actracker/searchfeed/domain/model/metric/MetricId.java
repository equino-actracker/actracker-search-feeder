package ovh.equino.actracker.searchfeed.domain.model.metric;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public final class MetricId extends EntityId {

    public MetricId(@JsonProperty("id") UUID id) {
        super(id);
    }
}
