package ovh.equino.actracker.searchfeed.domain.model;

public record EntityProcessedNotification<ID extends EntityId, ENTITY extends Entity<ID>>(
        ID entityId,
        Class<? extends EntityId> identifierType,
        Class<? extends ENTITY> entityType) {

    public EntityProcessedNotification(ID entityId, Class<? extends ENTITY> entityType) {
        this(entityId, entityId.getClass(), entityType);
    }
}
