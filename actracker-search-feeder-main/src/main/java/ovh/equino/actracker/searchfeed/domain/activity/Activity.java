package ovh.equino.actracker.searchfeed.domain.activity;

import ovh.equino.actracker.searchfeed.domain.Version;

import java.time.Instant;

public class Activity {

    private ActivityId id;
    private Version version;
    private Instant startTime;
    private Instant endTime;

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
