package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.services.activity.ActivityProcessor;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNullElse;

public class ActivityService {

    private final ActivityProcessor activityProcessor;

    ActivityService(ActivityProcessor activityProcessor) {
        this.activityProcessor = activityProcessor;
    }

    public void indexActivity(IndexActivityCommand indexActivityCommand) {
        Activity activity = new Activity(
                new ActivityId(indexActivityCommand.id()),
                new Version(indexActivityCommand.version()),
                indexActivityCommand.softDeleted(),
                new CreatorId(indexActivityCommand.creatorId()),
                indexActivityCommand.title(),
                indexActivityCommand.startTime(),
                indexActivityCommand.endTime(),
                indexActivityCommand.comment(),
                toTagIds(indexActivityCommand.tags()),
                requireNonNullElse(indexActivityCommand.metricValues(), emptyList())
        );

        activityProcessor.processActivity(activity);
    }

    private Set<TagId> toTagIds(Collection<UUID> tagUuids) {
        return requireNonNullElse(tagUuids, new ArrayList<UUID>())
                .stream()
                .map(TagId::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
