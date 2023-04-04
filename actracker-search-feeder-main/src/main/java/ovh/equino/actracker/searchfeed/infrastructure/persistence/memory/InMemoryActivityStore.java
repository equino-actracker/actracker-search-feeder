package ovh.equino.actracker.searchfeed.infrastructure.persistence.memory;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;

final class InMemoryActivityStore extends InMemoryEntityStore<ActivityId, Activity> implements ActivityStore {
}
