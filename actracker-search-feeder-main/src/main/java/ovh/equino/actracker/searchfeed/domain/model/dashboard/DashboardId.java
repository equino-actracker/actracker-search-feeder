package ovh.equino.actracker.searchfeed.domain.model.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;

import java.util.UUID;

public class DashboardId extends EntityId {

    public DashboardId(@JsonProperty("id") UUID id) {
        super(id);
    }
}
