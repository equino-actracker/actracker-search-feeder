package ovh.equino.actracker.searchfeed.domain.model.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;

import java.util.Set;

public record TagSetGraph(TagSet tagSet, Set<TagId> tags) implements EntityGraph<TagSetId> {

    @Override
    public TagSetId entityId() {
        return tagSet.id();
    }
}
