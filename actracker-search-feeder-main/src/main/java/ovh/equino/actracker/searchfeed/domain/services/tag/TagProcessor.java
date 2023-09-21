package ovh.equino.actracker.searchfeed.domain.services.tag;

import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentRefresh;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;
import java.util.List;

public final class TagProcessor extends EntityProcessor<TagId, Tag, TagRefreshedNotifier> {

    private final TagRefreshedNotifier tagRefreshedNotifier;
    private final ChildrenNotifierOfParentRefresh<TagId, ActivityId> activityNotifierOnTagRefresh;
    private final ChildrenNotifierOfParentRefresh<TagId, TagSetId> tagSetNotifierOnTagRefresh;

    TagProcessor(TagStore tagStore,
                 TagRefreshedNotifier tagRefreshedNotifier,
                 ChildrenNotifierOfParentRefresh<TagId, ActivityId> activityNotifierOnTagRefresh,
                 ChildrenNotifierOfParentRefresh<TagId, TagSetId> tagSetNotifierOnTagRefresh) {

        super(tagStore);
        this.tagRefreshedNotifier = tagRefreshedNotifier;
        this.activityNotifierOnTagRefresh = activityNotifierOnTagRefresh;
        this.tagSetNotifierOnTagRefresh = tagSetNotifierOnTagRefresh;
    }

    public void processTag(Tag tag) {
        super.processAndNotify(tag);
    }

    @Override
    protected TagRefreshedNotifier entityRefreshedNotifier() {
        return tagRefreshedNotifier;
    }

    @Override
    protected Collection<ChildrenNotifierOfParentRefresh<TagId, ? extends EntityId>> childrenNotifiers() {
        return List.of(activityNotifierOnTagRefresh, tagSetNotifierOnTagRefresh);
    }
}
