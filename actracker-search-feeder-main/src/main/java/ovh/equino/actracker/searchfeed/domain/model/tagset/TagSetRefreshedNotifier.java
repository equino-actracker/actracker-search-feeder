package ovh.equino.actracker.searchfeed.domain.model.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface TagSetRefreshedNotifier extends EntityRefreshedNotifier<TagSetId> {

    @Override
    default Class<TagSetId> supportedIdType() {
        return TagSetId.class;
    }
}
