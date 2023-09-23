package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityStore;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;

import java.util.Collection;

public interface ActivityStore extends EntityStore<ActivityId, Activity> {

    Collection<ActivityId> findByTag(TagId tagId);
}
