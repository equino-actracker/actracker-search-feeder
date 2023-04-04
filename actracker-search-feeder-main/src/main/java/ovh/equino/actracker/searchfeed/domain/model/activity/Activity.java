package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.Version;

import java.time.Instant;

public final class Activity extends Entity<ActivityId> {

        private final Instant startTime;
        private final Instant endTime;

        public Activity(ActivityId id, Version version, boolean softDeleted, Instant startTime, Instant endTime) {
                super(id, version, softDeleted);
                this.startTime = startTime;
                this.endTime = endTime;
        }
}
