package ovh.equino.actracker.searchfeed.domain.services.tag;

import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentProcessed;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;
import java.util.List;

public final class TagProcessor extends EntityProcessor<TagId, Tag, TagProcessedNotifier> {

    private final TagProcessedNotifier tagProcessedNotifier;
    private final ChildrenNotifierOfParentProcessed<TagId, ActivityId> activityNotifierOnTagProcessed;
    private final ChildrenNotifierOfParentProcessed<TagId, TagSetId> tagSetNotifierOnTagProcessed;

    TagProcessor(TagStore tagStore,
                 TagProcessedNotifier tagProcessedNotifier,
                 ChildrenNotifierOfParentProcessed<TagId, ActivityId> activityNotifierOnTagProcessed,
                 ChildrenNotifierOfParentProcessed<TagId, TagSetId> tagSetNotifierOnTagProcessed) {

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
    protected Collection<ChildrenNotifierOfParentProcessed<TagId, ? extends EntityId>> childrenNotifiers() {
        return List.of(activityNotifierOnTagProcessed, tagSetNotifierOnTagProcessed);
    }
}
