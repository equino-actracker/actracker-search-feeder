package ovh.equino.actracker.searchfeed.domain.model;

public interface EntityRefreshedNotificationHandler<ENTITY_ID extends EntityId> {

    void refreshedNotificationReceived(ENTITY_ID entityId);

    Class<ENTITY_ID> supportedIdType();
}
