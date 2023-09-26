package ovh.equino.actracker.searchfeed.domain.services.tag;

import ovh.equino.actracker.searchfeed.domain.model.tag.*;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

public final class TagIndexer extends EntityIndexer<TagId, Tag, TagGraph> {


    TagIndexer(TagStore tagStore, TagIndex tagIndex) {
        super(tagStore, tagIndex);
    }

    @Override
    protected TagGraph buildEntityGraph(Tag tag) {
        return new TagGraph(tag);
    }

    @Override
    public Class<Tag> supportedEntityType() {
        return Tag.class;
    }
}
