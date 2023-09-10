package ovh.equino.actracker.searchfeed.application.tag;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;
import ovh.equino.actracker.searchfeed.domain.model.metric.Metric;
import ovh.equino.actracker.searchfeed.domain.model.metric.MetricId;
import ovh.equino.actracker.searchfeed.domain.model.share.GranteeId;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.services.tag.TagIndexer;

import java.util.*;

import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class TagService {

    private final TagIndexer tagIndexer;

    TagService(TagIndexer tagIndexer) {
        this.tagIndexer = tagIndexer;
    }

    public void indexTag(IndexTagCommand indexTagCommand) {
        Tag tag = new Tag(
                new TagId(indexTagCommand.id()),
                new Version(indexTagCommand.version()),
                indexTagCommand.softDeleted(),
                new CreatorId(indexTagCommand.creatorId()),
                indexTagCommand.name(),
                toMetrics(indexTagCommand.metricWithDeletedFlag()),
                toGrantees(indexTagCommand.grantees())
        );

        tagIndexer.indexTag(tag);
    }

    private List<Metric> toMetrics(Map<UUID, Boolean> metricsMap) {
        return requireNonNullElse(metricsMap, new HashMap<UUID, Boolean>())
                .entrySet()
                .stream()
                .map(metricWithDeletedFlag -> new Metric(
                        new MetricId(metricWithDeletedFlag.getKey()),
                        metricWithDeletedFlag.getValue()
                ))
                .toList();
    }

    private Set<GranteeId> toGrantees(Set<UUID> grantees) {
        return requireNonNullElse(grantees, new HashSet<UUID>())
                .stream()
                .map(GranteeId::new)
                .collect(toUnmodifiableSet());
    }

}
