package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;
import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentRefresh;

class TagSetNotifierOnTagRefresh implements ChildrenNotifierOfParentRefresh<TagId, TagSetId> {

    private final TagSetStore tagSetStore;
    private final TagSetRefreshedNotifier tagSetRefreshedNotifier;

    TagSetNotifierOnTagRefresh(TagSetStore tagSetStore, TagSetRefreshedNotifier tagSetRefreshedNotifier) {
        this.tagSetStore = tagSetStore;
        this.tagSetRefreshedNotifier = tagSetRefreshedNotifier;
    }

    @Override
    public void notifyParentChanged(TagId tagId) {
        tagSetStore.findByTag(tagId)
                .stream()
                .map(id -> new EntityRefreshedNotification<>(id, TagSet.class))
                .forEach(tagSetRefreshedNotifier::notifyRefreshed);
    }
}
