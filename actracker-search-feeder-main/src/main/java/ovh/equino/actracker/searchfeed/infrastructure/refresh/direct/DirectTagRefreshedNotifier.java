package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.tag.TagIndexer;

class DirectTagRefreshedNotifier implements TagRefreshedNotifier {

    private final TagIndexer tagIndexer;

    DirectTagRefreshedNotifier(TagIndexer tagIndexer) {
        this.tagIndexer = tagIndexer;
    }

    @Override
    public void notifyRefreshed(TagId tagId) {
        tagIndexer.indexTag(tagId);
    }
}
