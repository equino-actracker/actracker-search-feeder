package ovh.equino.actracker.searchfeed.domain.services.tag;

import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagIndex;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class TagIndexer extends EntityIndexer<TagId, Tag> {


    TagIndexer(TagStore tagStore, TagIndex tagIndex) {
        super(tagStore, tagIndex);
    }

    public void indexTag(Tag tagToIndex) {
        super.index(tagToIndex);
    }

}
