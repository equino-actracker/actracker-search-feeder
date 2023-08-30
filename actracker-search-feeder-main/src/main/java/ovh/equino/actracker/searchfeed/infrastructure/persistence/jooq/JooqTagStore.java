package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;

import java.util.Optional;

final class JooqTagStore extends JooqEntityStore<TagId, Tag> implements TagStore {

    @Override
    public Optional<Tag> get(TagId id) {
        return Optional.empty();
    }

    @Override
    public void put(TagId id, Tag entity) {

    }
}
