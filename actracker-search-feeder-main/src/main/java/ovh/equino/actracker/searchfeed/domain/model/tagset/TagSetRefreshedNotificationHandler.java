package ovh.equino.actracker.searchfeed.domain.model.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotificationHandler;

public interface TagSetRefreshedNotificationHandler extends EntityRefreshedNotificationHandler<TagSetId> {

    @Override
    default Class<TagSetId> supportedIdType() {
        return TagSetId.class;
    }
}
