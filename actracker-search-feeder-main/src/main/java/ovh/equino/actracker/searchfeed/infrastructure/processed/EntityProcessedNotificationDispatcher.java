package ovh.equino.actracker.searchfeed.infrastructure.processed;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;

import java.util.List;

public class EntityProcessedNotificationDispatcher {

    private final List<EntityIndexer<?, ?, ?>> indexers;

    EntityProcessedNotificationDispatcher(List<EntityIndexer<?, ?, ?>> indexers) {
        this.indexers = indexers;
    }

    public <T extends EntityId, S extends Entity<T>> void dispatchNotification(EntityProcessedNotification<T, S> notification) {
        Class<? extends S> entityType = notification.entityType();
        indexerForType(entityType).index(notification.entityId());
    }

    private <T extends EntityId, S extends Entity<T>> EntityIndexer<T, S, ?> indexerForType(Class<S> entityType) {

        //noinspection unchecked
        return (EntityIndexer<T, S, ?>) indexers.stream()
//                .filter(indexer -> indexer.supportedEntityType().equals(entityType))
                .filter(indexer -> entityType.equals(indexer.supportedEntityType()))
                .findFirst()
                .orElseThrow(() -> {
                    String errorMessage = "Processed handler not found for notification of type %s"
                            .formatted(entityType.getCanonicalName());
                    return new RuntimeException(errorMessage);
                });
    }
}
