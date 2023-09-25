package ovh.equino.actracker.searchfeed.domain.model;

public record EntityRefreshedNotification<ID extends EntityId, ENTITY extends Entity<ID>>(
        ID entityId,
        Class<? extends ENTITY> entityType) {
}
