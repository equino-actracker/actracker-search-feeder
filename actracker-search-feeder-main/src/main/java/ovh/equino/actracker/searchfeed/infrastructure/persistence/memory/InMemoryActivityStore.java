package ovh.equino.actracker.searchfeed.infrastructure.persistence.memory;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryActivityStore implements ActivityStore {

    private final Map<ActivityId, Activity> storedActivities = new HashMap<>();

    @Override
    public Optional<Activity> get(ActivityId id) {
        return Optional.ofNullable(storedActivities.get(id));
    }

    @Override
    public void put(ActivityId id, Activity activity) {
        storedActivities.put(id, activity);
    }
}
