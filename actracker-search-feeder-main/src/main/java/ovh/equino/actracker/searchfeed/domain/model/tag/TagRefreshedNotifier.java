package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface TagRefreshedNotifier extends EntityRefreshedNotifier<TagId, Tag> {

    @Override
    default Class<Tag> supportedEntityType() {
        return Tag.class;
    }
}
