package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.tagset.TagSetIndexer;

class DirectTagSetRefreshedNotifier implements TagSetRefreshedNotifier {

    private final TagSetIndexer tagSetIndexer;

    DirectTagSetRefreshedNotifier(TagSetIndexer tagSetIndexer) {
        this.tagSetIndexer = tagSetIndexer;
    }

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<TagSetId, TagSet> entityRefreshedNotification) {
        tagSetIndexer.indexTagSet(entityRefreshedNotification.entityId());
    }
}
