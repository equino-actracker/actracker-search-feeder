package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetIndex;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class TagSetIndexer extends EntityIndexer<TagSetId, TagSet> {

    TagSetIndexer(TagSetStore tagSetStore, TagSetIndex tagSetIndex) {
        super(tagSetStore, tagSetIndex);
    }

    public void indexTagSet(TagSet tagSetToIndex) {
        super.index(tagSetToIndex);
    }
}
