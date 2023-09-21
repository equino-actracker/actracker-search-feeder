package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentRefresh;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.*;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

import java.util.Collection;

import static java.util.Collections.emptyList;

public final class TagSetProcessor extends EntityProcessor<TagSetId, TagSet, TagSetRefreshedNotifier> {

    private final TagSetRefreshedNotifier tagSetRefreshedNotifier;

    TagSetProcessor(TagSetStore tagSetStore, TagSetRefreshedNotifier tagSetRefreshedNotifier) {
        super(tagSetStore);
        this.tagSetRefreshedNotifier = tagSetRefreshedNotifier;
    }

    public void processTagSet(TagSet tagSet) {
        super.processAndNotify(tagSet);
    }

    @Override
    protected TagSetRefreshedNotifier entityRefreshedNotifier() {
        return tagSetRefreshedNotifier;
    }

    @Override
    protected Collection<ChildrenNotifierOfParentRefresh<TagSetId, ? extends EntityId>> childrenNotifiers() {
        return emptyList();
    }
}
