package ovh.equino.actracker.searchfeed.domain.model.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotifier;

public interface TagSetProcessedNotifier extends EntityProcessedNotifier<TagSetId, TagSet> {

    @Override
    default Class<TagSet> supportedEntityType() {
        return TagSet.class;
    }
}
