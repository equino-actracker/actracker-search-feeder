package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.*;

import java.util.Collection;
import java.util.Optional;

public abstract class EntityProcessor<ID extends EntityId, ENTITY extends Entity<ID>, NOTIFIER extends EntityProcessedNotifier<ID, ENTITY>> {

    private final EntityStore<ID, ENTITY> entityStore;

    protected EntityProcessor(EntityStore<ID, ENTITY> entityStore) {
        this.entityStore = entityStore;
    }

    protected void processAndNotify(ENTITY entityToProcess) {
        ID entityId = entityToProcess.id();
        Optional<ENTITY> storedEntity = entityStore.get(entityId);

        storedEntity.ifPresentOrElse(
                (stored) -> processIfOverrides(stored, entityToProcess),
                () -> processIfNotDeleted(entityToProcess)
        );
    }

    private void processIfNotDeleted(ENTITY entity) {
        entityStore.put(entity.id(), entity);
        if (entity.isNotDeleted()) {
            notifyProcessed(entity.id());
        }
    }

    private void processIfOverrides(ENTITY storedEntity, ENTITY newEntity) {
        if (newEntity.shouldReplace(storedEntity)) {
            entityStore.put(newEntity.id(), newEntity);
            notifyProcessed(newEntity.id());
        }
    }

    private void notifyProcessed(ID entityId) {
        Class<ENTITY> processedEntityType = entityProcessedNotifier().supportedEntityType();
        entityProcessedNotifier().notifyProcessed(new EntityProcessedNotification<>(entityId, processedEntityType));
        childrenNotifiers().forEach(childrenNotifier -> childrenNotifier.notifyParentChanged(entityId));
    }

    protected abstract NOTIFIER entityProcessedNotifier();

    protected abstract Collection<ChildrenNotifierOfParentProcessed<ID, ? extends EntityId>> childrenNotifiers();
}
