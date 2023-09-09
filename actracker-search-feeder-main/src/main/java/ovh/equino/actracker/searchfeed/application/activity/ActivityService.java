package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.metric.MetricId;
import ovh.equino.actracker.searchfeed.domain.model.metricValue.MetricValue;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.services.activity.ActivityIndexer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

public class ActivityService {

    private final ActivityIndexer activityIndexer;

    ActivityService(ActivityIndexer activityIndexer) {
        this.activityIndexer = activityIndexer;
    }

    public void indexActivity(IndexActivityCommand indexActivityCommand) {
        Activity activity = new Activity(
                new ActivityId(indexActivityCommand.id()),
                new Version(indexActivityCommand.version()),
                indexActivityCommand.softDeleted(),
                indexActivityCommand.title(),
                indexActivityCommand.startTime(),
                indexActivityCommand.endTime(),
                indexActivityCommand.comment(),
                toTagIds(indexActivityCommand.tags()),
                toMetricValues(indexActivityCommand.metricValueById())
        );

        activityIndexer.indexActivity(activity);
    }

    private Set<TagId> toTagIds(Collection<UUID> tagUuids) {
        return requireNonNullElse(tagUuids, new ArrayList<UUID>())
                .stream()
                .map(TagId::new)
                .collect(Collectors.toUnmodifiableSet());
    }

    private List<MetricValue> toMetricValues(Map<UUID, BigDecimal> metricValuesByIds) {
        return requireNonNullElse(metricValuesByIds, new HashMap<UUID, BigDecimal>())
                .entrySet()
                .stream()
                .map(metricValueById -> toMetricValue(metricValueById.getKey(), metricValueById.getValue()))
                .toList();

    }

    private MetricValue toMetricValue(UUID metricUuid, BigDecimal value) {
        return new MetricValue(new MetricId(metricUuid), value);
    }

}
