package ovh.equino.actracker.searchfeed.domain.services.tag;

import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

class TagProcessor extends EntityProcessor<TagId, Tag, TagRefreshedNotifier> {

    private final TagRefreshedNotifier tagRefreshedNotifier;

    protected TagProcessor(TagStore tagStore, TagRefreshedNotifier tagRefreshedNotifier) {
        super(tagStore);
        this.tagRefreshedNotifier = tagRefreshedNotifier;
    }

    @Override
    protected void processAndNotify(Tag tag) {
        super.processAndNotify(tag);
    }

    @Override
    protected TagRefreshedNotifier entityRefreshedNotifier() {
        return tagRefreshedNotifier;
    }
}
