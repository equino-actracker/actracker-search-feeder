package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.model.tagset.*;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

import java.util.Set;

public final class TagSetIndexer extends EntityIndexer<TagSetId, TagSet, TagSetGraph> {

    private final TagStore tagStore;

    TagSetIndexer(TagSetStore tagSetStore, TagSetIndex tagSetIndex, TagStore tagStore) {
        super(tagSetStore, tagSetIndex);
        this.tagStore = tagStore;
    }

    @Override
    protected TagSetGraph buildEntityGraph(TagSet tagSet) {
        Set<TagId> tags = tagStore.nonDeletedTags(tagSet.tags());
        return new TagSetGraph(tagSet, tags);
    }

    @Override
    public Class<TagSet> supportedEntityType() {
        return TagSet.class;
    }
}
