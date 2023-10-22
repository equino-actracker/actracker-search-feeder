package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.activity.*;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

import java.util.Collection;
import java.util.Set;

public final class ActivityIndexer extends EntityIndexer<ActivityId, Activity, ActivityGraph> {

    private final TagStore tagStore;

    ActivityIndexer(ActivityStore activityStore, ActivityIndex activityIndex, TagStore tagStore) {
        super(activityStore, activityIndex);
        this.tagStore = tagStore;
    }

    @Override
    protected ActivityGraph buildEntityGraph(Activity activity) {
        Collection<Tag> tags = tagStore.nonDeletedTags(activity.tags());
        return new ActivityGraph(activity, tags);
    }

    @Override
    public Class<Activity> supportedEntityType() {
        return Activity.class;
    }
}
