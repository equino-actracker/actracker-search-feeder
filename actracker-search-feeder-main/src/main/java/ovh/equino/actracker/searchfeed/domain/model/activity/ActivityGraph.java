package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;

import java.util.Collection;

public record ActivityGraph(Activity activity, Collection<Tag> tags) implements EntityGraph<ActivityId> {

    @Override
    public ActivityId entityId() {
        return activity.id();
    }
}
