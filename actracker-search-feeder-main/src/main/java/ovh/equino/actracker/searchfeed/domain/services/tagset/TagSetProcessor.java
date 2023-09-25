package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentProcessed;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.*;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;

import static java.util.Collections.emptyList;

public final class TagSetProcessor extends EntityProcessor<TagSetId, TagSet, TagSetProcessedNotifier> {

    private final TagSetProcessedNotifier tagSetProcessedNotifier;

    TagSetProcessor(TagSetStore tagSetStore, TagSetProcessedNotifier tagSetProcessedNotifier) {
        super(tagSetStore);
        this.tagSetProcessedNotifier = tagSetProcessedNotifier;
    }

    public void processTagSet(TagSet tagSet) {
        super.processAndNotify(tagSet);
    }

    @Override
    protected TagSetProcessedNotifier entityProcessedNotifier() {
        return tagSetProcessedNotifier;
    }

    @Override
    protected Collection<ChildrenNotifierOfParentProcessed<TagSetId, ? extends EntityId>> childrenNotifiers() {
        return emptyList();
    }
}
