package ovh.equino.actracker.searchfeed.application.activity;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.services.IndexActivityService;

public class ActivityService {

    private final IndexActivityService activityIndexer;

    ActivityService(IndexActivityService activityIndexer) {
        this.activityIndexer = activityIndexer;
    }

    // Use command as a parameter.
    public void indexActivity(IndexActivityCommand command) {
        Activity activity = new Activity(
                new ActivityId(command.id()),
                new Version(command.version()),
                command.startTime(),
                command.endTime()
        );

        activityIndexer.indexActivity(activity);
    }

}
