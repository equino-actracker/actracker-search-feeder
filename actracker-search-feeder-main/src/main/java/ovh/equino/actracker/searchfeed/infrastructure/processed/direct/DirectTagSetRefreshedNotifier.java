package ovh.equino.actracker.searchfeed.infrastructure.processed.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetProcessedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.tagset.TagSetIndexer;

class DirectTagSetRefreshedNotifier implements TagSetProcessedNotifier {

    private final TagSetIndexer tagSetIndexer;

    DirectTagSetRefreshedNotifier(TagSetIndexer tagSetIndexer) {
        this.tagSetIndexer = tagSetIndexer;
    }

    @Override
    public void notifyProcessed(EntityProcessedNotification<TagSetId, TagSet> entityProcessedNotification) {
        tagSetIndexer.indexTagSet(entityProcessedNotification.entityId());
    }
}
