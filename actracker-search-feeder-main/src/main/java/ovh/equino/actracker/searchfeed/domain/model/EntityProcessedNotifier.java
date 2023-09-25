package ovh.equino.actracker.searchfeed.domain.model;

public interface EntityProcessedNotifier<ID extends EntityId, ENTITY extends Entity<ID>> {

    void notifyProcessed(EntityProcessedNotification<ID, ENTITY> entityProcessedNotification);

    Class<ENTITY> supportedEntityType();
}
