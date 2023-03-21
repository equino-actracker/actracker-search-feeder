package ovh.equino.actracker.searchfeed.domain.model.activity;

import java.util.Optional;

public interface ActivityStore {

    Optional<Activity> get(ActivityId id);

    void put(ActivityId id, Activity activity);
}
