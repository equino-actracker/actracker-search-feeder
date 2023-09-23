package ovh.equino.actracker.searchfeed.domain.model.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityStore;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;

import java.util.Collection;

public interface TagSetStore extends EntityStore<TagSetId, TagSet> {

    Collection<TagSetId> findByTag(TagId tagId);
}
