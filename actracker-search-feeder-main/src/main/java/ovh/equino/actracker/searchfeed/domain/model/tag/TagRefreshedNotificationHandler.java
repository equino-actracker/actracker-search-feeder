package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotificationHandler;

public interface TagRefreshedNotificationHandler extends EntityRefreshedNotificationHandler<TagId> {

    @Override
    default Class<TagId> supportedIdType() {
        return TagId.class;
    }
}
