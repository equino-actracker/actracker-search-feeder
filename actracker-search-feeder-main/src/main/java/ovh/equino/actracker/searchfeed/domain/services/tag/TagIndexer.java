package ovh.equino.actracker.searchfeed.domain.services.tag;

import ovh.equino.actracker.searchfeed.domain.model.tag.*;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class TagIndexer extends EntityIndexer<TagId, Tag, TagGraph> {


    TagIndexer(TagStore tagStore, TagIndex tagIndex) {
        super(tagStore, tagIndex);
    }

    public void indexTag(Tag tagToIndex) {
        super.index(tagToIndex);
    }

    @Override
    protected TagGraph buildEntityGraph(Tag entity) {
        return new TagGraph();
    }
}
