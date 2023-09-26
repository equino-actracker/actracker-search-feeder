package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;
import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentProcessed;

class TagSetNotifierOnTagProcessed implements ChildrenNotifierOfParentProcessed<TagId> {

    private final TagSetStore tagSetStore;
    private final TagSetProcessedNotifier tagSetProcessedNotifier;

    TagSetNotifierOnTagProcessed(TagSetStore tagSetStore, TagSetProcessedNotifier tagSetProcessedNotifier) {
        this.tagSetStore = tagSetStore;
        this.tagSetProcessedNotifier = tagSetProcessedNotifier;
    }

    @Override
    public void notifyParentChanged(TagId tagId) {
        tagSetStore.findByTag(tagId)
                .stream()
                .map(id -> new EntityProcessedNotification<>(id, TagSet.class))
                .forEach(tagSetProcessedNotifier::notifyProcessed);
    }
}
