package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface TagRefreshedNotifier extends EntityRefreshedNotifier<TagId> {

    @Override
    default Class<TagId> supportedIdType() {
        return TagId.class;
    }
}
