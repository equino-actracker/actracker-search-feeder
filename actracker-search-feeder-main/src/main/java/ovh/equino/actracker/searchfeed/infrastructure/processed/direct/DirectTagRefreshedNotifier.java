package ovh.equino.actracker.searchfeed.infrastructure.processed.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.tag.TagIndexer;

class DirectTagRefreshedNotifier implements TagProcessedNotifier {

    private final TagIndexer tagIndexer;

    DirectTagRefreshedNotifier(TagIndexer tagIndexer) {
        this.tagIndexer = tagIndexer;
    }

    @Override
    public void notifyProcessed(EntityProcessedNotification<TagId, Tag> entityProcessedNotification) {
        tagIndexer.indexTag(entityProcessedNotification.entityId());
    }
}
