package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.*;

import java.util.Collection;
import java.util.Optional;

public abstract class EntityProcessor<ID extends EntityId, ENTITY extends Entity<ID>, NOTIFIER extends EntityRefreshedNotifier<ID, ENTITY>> {

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
            notifyRefreshed(entity.id());
        }
    }

    private void processIfOverrides(ENTITY storedEntity, ENTITY newEntity) {
        if (newEntity.shouldReplace(storedEntity)) {
            entityStore.put(newEntity.id(), newEntity);
            notifyRefreshed(newEntity.id());
        }
    }

    private void notifyRefreshed(ID entityId) {
        Class<ENTITY> refreshedEntityType = entityRefreshedNotifier().supportedEntityType();
        entityRefreshedNotifier().notifyRefreshed(new EntityRefreshedNotification<>(entityId, refreshedEntityType));
        childrenNotifiers().forEach(childrenNotifier -> childrenNotifier.notifyParentChanged(entityId));
    }

    protected abstract NOTIFIER entityRefreshedNotifier();

    protected abstract Collection<ChildrenNotifierOfParentRefresh<ID, ? extends EntityId>> childrenNotifiers();
}
