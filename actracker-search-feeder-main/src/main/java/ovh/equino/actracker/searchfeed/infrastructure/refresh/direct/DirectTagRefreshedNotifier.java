package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.services.tag.TagIndexer;

class DirectTagRefreshedNotifier implements TagRefreshedNotifier {

    private final TagIndexer tagIndexer;

    DirectTagRefreshedNotifier(TagIndexer tagIndexer) {
        this.tagIndexer = tagIndexer;
    }

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<TagId, Tag> entityRefreshedNotification) {
        tagIndexer.indexTag(entityRefreshedNotification.entityId());
    }
}
