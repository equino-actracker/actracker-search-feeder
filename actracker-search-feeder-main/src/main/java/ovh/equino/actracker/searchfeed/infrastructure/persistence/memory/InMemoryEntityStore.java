package ovh.equino.actracker.searchfeed.infrastructure.persistence.memory;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class InMemoryEntityStore<Id extends EntityId, E extends Entity<Id>> implements EntityStore<Id, E> {

    private final Map<Id, E> storedEntities = new HashMap<>();

    @Override
    public Optional<E> get(Id id) {
        return Optional.ofNullable(storedEntities.get(id));
    }

    @Override
    public void put(Id id, E entity) {
        storedEntities.put(id, entity);
    }

}
