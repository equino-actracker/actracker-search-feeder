package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.EntityStore;

import java.util.Collection;
import java.util.Set;

public interface TagStore extends EntityStore<TagId, Tag> {

    Set<TagId> nonDeletedTags(Collection<TagId> tagIds);
}
