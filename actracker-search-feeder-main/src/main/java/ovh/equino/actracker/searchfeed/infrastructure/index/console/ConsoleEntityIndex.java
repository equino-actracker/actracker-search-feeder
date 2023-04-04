package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityIndex;

abstract class ConsoleEntityIndex<ID extends EntityId, ENTITY extends Entity<ID>> implements EntityIndex<ID, ENTITY> {

    @Override
    public void index(ENTITY entity) {
        String entityType = entity.getClass().getSimpleName();
        System.out.printf("Indexed %s with ID %s%n", entityType, entity.id());
    }

    @Override
    public void delete(ID id) {
        String entityIdType = id.getClass().getSimpleName();
        System.out.printf("Deleted entity with ID %s of type %s%n", id.id(), entityIdType);
    }
}
