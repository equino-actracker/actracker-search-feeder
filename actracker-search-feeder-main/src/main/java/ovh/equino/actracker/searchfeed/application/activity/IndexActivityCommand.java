package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.model.metricValue.MetricValue;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public record IndexActivityCommand(

        UUID id,
        boolean softDeleted,
        long version,
        UUID creatorId,
        String title,
        Instant startTime,
        Instant endTime,
        String comment,
        Set<UUID> tags,
        Collection<MetricValue> metricValues
) {
}
