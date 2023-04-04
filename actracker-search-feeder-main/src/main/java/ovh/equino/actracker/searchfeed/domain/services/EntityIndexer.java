package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityIndex;
import ovh.equino.actracker.searchfeed.domain.model.EntityStore;

import java.util.Optional;

public abstract class EntityIndexer<ID extends EntityId, ENTITY extends Entity<ID>> {

    private final EntityStore<ID, ENTITY> entityStore;
    private final EntityIndex<ID, ENTITY> entityIndex;


    protected EntityIndexer(EntityStore<ID, ENTITY> entityStore, EntityIndex<ID, ENTITY> entityIndex) {
        this.entityStore = entityStore;
        this.entityIndex = entityIndex;
    }

    protected void index(ENTITY entityToIndex) {
        ID entityId = entityToIndex.id();
        Optional<ENTITY> storedEntity = entityStore.get(entityId);

        storedEntity.ifPresentOrElse(
                (stored) -> saveOrDeleteIfOverrides(stored, entityToIndex),
                () -> indexIfNotDeleted(entityToIndex)
        );
    }

    private void indexIfNotDeleted(ENTITY entity) {
        entityStore.put(entity.id(), entity);
        if (entity.isNotDeleted()) {
            entityIndex.index(entity);
        }
    }

    private void saveOrDeleteIfOverrides(ENTITY storedEntity, ENTITY newEntity) {
        if (newEntity.shouldReplace(storedEntity)) {
            reindexOrDelete(newEntity);
        }
    }

    private void reindexOrDelete(ENTITY entity) {
        ID entityId = entity.id();
        entityStore.put(entityId, entity);
        if (entity.isNotDeleted()) {
            entityIndex.index(entity);
        } else {
            entityIndex.delete(entityId);
        }
    }
}
