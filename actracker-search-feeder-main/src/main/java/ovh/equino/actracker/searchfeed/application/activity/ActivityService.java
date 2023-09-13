package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.services.activity.ActivityIndexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
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
                new CreatorId(indexActivityCommand.creatorId()),
                indexActivityCommand.title(),
                indexActivityCommand.startTime(),
                indexActivityCommand.endTime(),
                indexActivityCommand.comment(),
                toTagIds(indexActivityCommand.tags())
        );

        activityIndexer.indexActivity(activity);
    }

    private Set<TagId> toTagIds(Collection<UUID> tagUuids) {
        return requireNonNullElse(tagUuids, new ArrayList<UUID>())
                .stream()
                .map(TagId::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
