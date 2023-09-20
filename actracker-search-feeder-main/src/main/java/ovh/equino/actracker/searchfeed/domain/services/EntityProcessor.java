package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;
import ovh.equino.actracker.searchfeed.domain.model.EntityStore;

import java.util.Optional;

public abstract class EntityProcessor<ID extends EntityId, ENTITY extends Entity<ID>, NOTIFIER extends EntityRefreshedNotifier<ID>> {

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
            entityRefreshedNotifier().notifyRefreshed(entity.id());
        }
    }

    private void processIfOverrides(ENTITY storedEntity, ENTITY newEntity) {
        if (newEntity.shouldReplace(storedEntity)) {
            entityStore.put(newEntity.id(), newEntity);
            entityRefreshedNotifier().notifyRefreshed(newEntity.id());
        }
    }

    protected abstract NOTIFIER entityRefreshedNotifier();
}
