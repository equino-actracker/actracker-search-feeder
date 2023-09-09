package ovh.equino.actracker.searchfeed.application.activity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record IndexActivityCommand(

        UUID id,
        boolean softDeleted,
        long version,
        String title,
        Instant startTime,
        Instant endTime,
        String comment,
        Set<UUID> tags,
        Map<UUID, BigDecimal> metricValueById

) {
}
