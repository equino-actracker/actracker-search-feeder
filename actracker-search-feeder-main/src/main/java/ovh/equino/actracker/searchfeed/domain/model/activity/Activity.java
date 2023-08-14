package ovh.equino.actracker.searchfeed.domain.model.activity;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.metricValue.MetricValue;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public final class Activity extends Entity<ActivityId> {

    private final Instant startTime;
    private final Instant endTime;
    private final String comment;
    private final Set<TagId> tags;
    private final List<MetricValue> metricValues;

    public Activity(ActivityId id,
                    Version version,
                    boolean softDeleted,
                    Instant startTime,
                    Instant endTime,
                    String comment,
                    Set<TagId> tags,
                    List<MetricValue> metricValues) {

        super(id, version, softDeleted);
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
        this.tags = tags;
        this.metricValues = metricValues;
    }
}
