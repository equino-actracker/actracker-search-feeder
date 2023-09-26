package ovh.equino.actracker.searchfeed.domain.services.tag;

import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentProcessed;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;
import java.util.List;

public final class TagProcessor extends EntityProcessor<TagId, Tag, TagProcessedNotifier> {

    private final TagProcessedNotifier tagProcessedNotifier;
    private final ChildrenNotifierOfParentProcessed<TagId> activityNotifierOnTagProcessed;
    private final ChildrenNotifierOfParentProcessed<TagId> tagSetNotifierOnTagProcessed;

    TagProcessor(TagStore tagStore,
                 TagProcessedNotifier tagProcessedNotifier,
                 ChildrenNotifierOfParentProcessed<TagId> activityNotifierOnTagProcessed,
                 ChildrenNotifierOfParentProcessed<TagId> tagSetNotifierOnTagProcessed) {

        super(tagStore);
        this.tagProcessedNotifier = tagProcessedNotifier;
        this.activityNotifierOnTagProcessed = activityNotifierOnTagProcessed;
        this.tagSetNotifierOnTagProcessed = tagSetNotifierOnTagProcessed;
    }

    public void processTag(Tag tag) {
        super.processAndNotify(tag);
    }

    @Override
    protected TagProcessedNotifier entityProcessedNotifier() {
        return tagProcessedNotifier;
    }

    @Override
    protected Collection<ChildrenNotifierOfParentProcessed<TagId>> childrenNotifiers() {
        return List.of(activityNotifierOnTagProcessed, tagSetNotifierOnTagProcessed);
    }
}
