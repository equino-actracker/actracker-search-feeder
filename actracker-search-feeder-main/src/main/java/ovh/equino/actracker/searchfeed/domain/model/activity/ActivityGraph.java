package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;

import java.util.Set;

public record ActivityGraph(Activity activity, Set<TagId> tagIds) implements EntityGraph<ActivityId> {

    @Override
    public ActivityId entityId() {
        return activity.id();
    }
}
