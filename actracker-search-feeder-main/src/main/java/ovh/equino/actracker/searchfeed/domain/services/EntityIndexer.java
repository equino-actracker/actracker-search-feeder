package ovh.equino.actracker.searchfeed.domain.services;

import ovh.equino.actracker.searchfeed.domain.model.*;

public abstract class EntityIndexer<ID extends EntityId, ENTITY extends Entity<ID>, GRAPH extends EntityGraph<ID>> {

    private final EntityStore<ID, ENTITY> entityStore;
    private final EntityIndex<ID, GRAPH> entityIndex;

    protected EntityIndexer(EntityStore<ID, ENTITY> entityStore, EntityIndex<ID, GRAPH> entityIndex) {
        this.entityStore = entityStore;
        this.entityIndex = entityIndex;
    }

    protected void index(ID entityId) {
        ENTITY entity = entityStore.get(entityId)
                .orElseThrow(() -> {
                    String errorMessage = "Entity with ID=%s of type %s not found in entity store".formatted(
                            entityId.toString(),
                            entityId.getClass().getSimpleName()
                    );
                    return new RuntimeException(errorMessage);
                });

        if (entity.isNotDeleted()) {
            GRAPH entityGraph = buildEntityGraph(entity);
            entityIndex.index(entityGraph);
        } else {
            entityIndex.delete(entityId);
        }
    }

    protected abstract GRAPH buildEntityGraph(ENTITY entity);
}
