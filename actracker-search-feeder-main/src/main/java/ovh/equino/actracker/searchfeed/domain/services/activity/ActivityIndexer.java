package ovh.equino.actracker.searchfeed.domain.services.activity;

import ovh.equino.actracker.searchfeed.domain.model.activity.*;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

import java.util.Set;

public final class ActivityIndexer extends EntityIndexer<ActivityId, Activity, ActivityGraph> {

    private final TagStore tagStore;

    ActivityIndexer(ActivityStore activityStore, ActivityIndex activityIndex, TagStore tagStore) {
        super(activityStore, activityIndex);
        this.tagStore = tagStore;
    }

    public void indexActivity(Activity activityToIndex) {
        super.index(activityToIndex);
    }

    @Override
    protected ActivityGraph buildEntityGraph(Activity activity) {
        Set<TagId> tags = tagStore.nonDeletedTags(activity.tags());
        return new ActivityGraph(activity, tags);
    }
}
