package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotifier;

public interface TagProcessedNotifier extends EntityProcessedNotifier<TagId, Tag> {

    @Override
    default Class<Tag> supportedEntityType() {
        return Tag.class;
    }
}
