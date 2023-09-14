package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;

public record TagGraph(Tag tag) implements EntityGraph<TagId> {

    @Override
    public TagId entityId() {
        return tag.id();
    }
}
