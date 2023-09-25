package ovh.equino.actracker.searchfeed.domain.model;

public record EntityProcessedNotification<ID extends EntityId, ENTITY extends Entity<ID>>(
        ID entityId,
        Class<? extends ENTITY> entityType) {
}
