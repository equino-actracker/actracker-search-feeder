package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.EntityStore;

import java.util.Collection;
import java.util.Set;

public interface TagStore extends EntityStore<TagId, Tag> {

    Collection<Tag> nonDeletedTags(Collection<TagId> tags);
}
