package ovh.equino.actracker.searchfeed.domain.model.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;

import java.util.Collection;

public record TagSetGraph(TagSet tagSet, Collection<Tag> tags) implements EntityGraph<TagSetId> {

    @Override
    public TagSetId entityId() {
        return tagSet.id();
    }
}
