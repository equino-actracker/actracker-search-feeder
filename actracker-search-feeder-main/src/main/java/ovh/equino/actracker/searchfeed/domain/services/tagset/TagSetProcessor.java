package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

class TagSetProcessor extends EntityProcessor<TagSetId, TagSet, TagSetRefreshedNotifier> {

    private final TagSetRefreshedNotifier tagSetRefreshedNotifier;

    protected TagSetProcessor(TagSetStore tagSetStore, TagSetRefreshedNotifier tagSetRefreshedNotifier) {
        super(tagSetStore);
        this.tagSetRefreshedNotifier = tagSetRefreshedNotifier;
    }

    @Override
    protected void processAndNotify(TagSet tagSet) {
        super.processAndNotify(tagSet);
    }

    @Override
    protected TagSetRefreshedNotifier entityRefreshedNotifier() {
        return tagSetRefreshedNotifier;
    }
}
