package ovh.equino.actracker.searchfeed.domain.model.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public Activity(@JsonProperty("id") ActivityId id,
                    @JsonProperty("version") Version version,
                    @JsonProperty("softDeleted") boolean softDeleted,
                    @JsonProperty("startTime") Instant startTime,
                    @JsonProperty("endTime") Instant endTime,
                    @JsonProperty("comment") String comment,
                    @JsonProperty("tags") Set<TagId> tags,
                    @JsonProperty("metricValues") List<MetricValue> metricValues) {

        super(id, version, softDeleted);
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
        this.tags = tags;
        this.metricValues = metricValues;
    }
}
