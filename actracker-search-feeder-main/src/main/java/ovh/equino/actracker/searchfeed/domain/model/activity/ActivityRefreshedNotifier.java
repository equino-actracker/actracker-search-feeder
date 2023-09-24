package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

public interface ActivityRefreshedNotifier extends EntityRefreshedNotifier<ActivityId> {

    @Override
    default Class<ActivityId> supportedIdType() {
        return ActivityId.class;
    }
}
