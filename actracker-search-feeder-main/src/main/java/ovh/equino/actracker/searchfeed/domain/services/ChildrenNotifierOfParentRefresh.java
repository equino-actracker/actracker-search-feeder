package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.EntityId;

public interface ChildrenNotifierOfParentRefresh<PARENT_ID extends EntityId, CHILD_ID extends EntityId> {

    void notifyParentChanged(PARENT_ID parentId);
}
