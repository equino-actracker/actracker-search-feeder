package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;

public record ActivityGraph(Activity activity) implements EntityGraph<ActivityId> {

    @Override
    public ActivityId entityId() {
        return activity.id();
    }
}
