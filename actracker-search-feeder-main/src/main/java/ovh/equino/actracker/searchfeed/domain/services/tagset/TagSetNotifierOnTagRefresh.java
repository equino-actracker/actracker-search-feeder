package ovh.equino.actracker.searchfeed.domain.services.tagset;

import ovh.equino.actracker.searchfeed.domain.services.ChildrenNotifierOfParentRefresh;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;

class TagSetNotifierOnTagRefresh implements ChildrenNotifierOfParentRefresh<TagId, TagSetId> {

    private final TagSetStore tagSetStore;
    private final TagSetRefreshedNotifier tagSetRefreshedNotifier;

    TagSetNotifierOnTagRefresh(TagSetStore tagSetStore, TagSetRefreshedNotifier tagSetRefreshedNotifier) {
        this.tagSetStore = tagSetStore;
        this.tagSetRefreshedNotifier = tagSetRefreshedNotifier;
    }

    @Override
    public void notifyParentChanged(TagId tagId) {
        System.out.printf("Updating tag sets containing tag with ID=%s%n", tagId.toString());
    }
}
