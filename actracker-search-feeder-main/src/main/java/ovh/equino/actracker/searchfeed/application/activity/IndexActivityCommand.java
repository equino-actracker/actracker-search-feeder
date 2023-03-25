package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.model.Version;

import java.time.Instant;
import java.util.UUID;

public record IndexActivityCommand(

        UUID id,
        long version,
        Instant startTime,
        Instant endTime

) {
}
