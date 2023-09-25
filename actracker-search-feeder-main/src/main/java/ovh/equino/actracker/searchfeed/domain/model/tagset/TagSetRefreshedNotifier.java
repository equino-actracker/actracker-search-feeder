package ovh.equino.actracker.searchfeed.domain.model.tagset;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface TagSetRefreshedNotifier extends EntityRefreshedNotifier<TagSetId, TagSet> {

    @Override
    default Class<TagSet> supportedEntityType() {
        return TagSet.class;
    }
}
