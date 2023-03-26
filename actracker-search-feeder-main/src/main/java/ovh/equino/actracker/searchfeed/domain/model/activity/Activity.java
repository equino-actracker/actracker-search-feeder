package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.Version;

import java.time.Instant;

public record Activity(

        ActivityId id,
        boolean saveDeleted,
        Version version,
        Instant startTime,
        Instant endTime

) {

    public ActivityId id() {
        return id;
    }

    public boolean shouldReplace(Activity otherActivity) {
        if (otherActivity == null) {
            return true;
        }
        return this.version.isLaterThan(otherActivity.version);
    }
}
