package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityIndex;

abstract class ConsoleEntityIndex<Id extends EntityId, E extends Entity<Id>> implements EntityIndex<Id, E> {

    @Override
    public void index(E entity) {
        String entityType = entity.getClass().getSimpleName();
        System.out.printf("Indexed %s with ID %s%n", entityType, entity.id());
    }

    @Override
    public void delete(Id id) {
        String entityIdType = id.getClass().getSimpleName();
        System.out.printf("Deleted entity with ID %s of type %s%n", id.id(), entityIdType);
    }
}
