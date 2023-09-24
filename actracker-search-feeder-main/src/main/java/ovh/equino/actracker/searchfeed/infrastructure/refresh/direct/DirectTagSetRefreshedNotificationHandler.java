package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotificationHandler;
import ovh.equino.actracker.searchfeed.domain.services.tagset.TagSetIndexer;

class DirectTagSetRefreshedNotificationHandler implements TagSetRefreshedNotificationHandler {

    private final TagSetIndexer tagSetIndexer;

    DirectTagSetRefreshedNotificationHandler(TagSetIndexer tagSetIndexer) {
        this.tagSetIndexer = tagSetIndexer;
    }

    @Override
    public void refreshedNotificationReceived(TagSetId tagSetId) {
        tagSetIndexer.indexTagSet(tagSetId);
    }
}
