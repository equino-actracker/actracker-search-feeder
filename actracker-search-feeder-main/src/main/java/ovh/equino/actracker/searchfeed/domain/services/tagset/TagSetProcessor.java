package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

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
}
