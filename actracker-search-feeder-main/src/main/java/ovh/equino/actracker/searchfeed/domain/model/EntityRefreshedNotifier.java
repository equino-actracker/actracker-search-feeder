package ovh.equino.actracker.searchfeed.domain.model;

public interface EntityRefreshedNotifier<ID extends EntityId, ENTITY extends Entity<ID>> {

    void notifyRefreshed(EntityRefreshedNotification<ID, ENTITY> entityRefreshedNotification);

    Class<ENTITY> supportedEntityType();
}
