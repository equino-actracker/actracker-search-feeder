package ovh.equino.actracker.searchfeed.domain.model;

public interface EntityRefreshedNotifier<ENTITY_ID extends EntityId> {

    void notifyRefreshed(ENTITY_ID entityId);

    Class<ENTITY_ID> supportedIdType();
}
