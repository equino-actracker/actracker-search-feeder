package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.services.ActivityIndexer;

public class ActivityService {

    private final ActivityIndexer activityIndexer;

    ActivityService(ActivityIndexer activityIndexer) {
        this.activityIndexer = activityIndexer;
    }

    public void indexActivity(IndexActivityCommand command) {
        Activity activity = new Activity(
                new ActivityId(command.id()),
                command.softDeleted(),
                new Version(command.version()),
                command.startTime(),
                command.endTime()
        );

        activityIndexer.indexActivity(activity);
    }

}
