package ovh.equino.actracker.searchfeed.infrastructure.persistence.memory;

import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;

final class InMemoryTagStore extends InMemoryEntityStore<TagId, Tag> implements TagStore {
}
