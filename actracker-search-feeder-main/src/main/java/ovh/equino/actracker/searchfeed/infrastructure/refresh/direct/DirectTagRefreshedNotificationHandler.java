package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagRefreshedNotificationHandler;
import ovh.equino.actracker.searchfeed.domain.services.tag.TagIndexer;

class DirectTagRefreshedNotificationHandler implements TagRefreshedNotificationHandler {

    private final TagIndexer tagIndexer;

    DirectTagRefreshedNotificationHandler(TagIndexer tagIndexer) {
        this.tagIndexer = tagIndexer;
    }

    @Override
    public void refreshedNotificationReceived(TagId tagId) {
        tagIndexer.indexTag(tagId);
    }
}
