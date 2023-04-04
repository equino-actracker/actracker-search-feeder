package ovh.equino.actracker.searchfeed.infrastructure.persistence.memory;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class InMemoryEntityStore<ID extends EntityId, ENTITY extends Entity<ID>> implements EntityStore<ID, ENTITY> {

    private final Map<ID, ENTITY> storedEntities = new HashMap<>();

    @Override
    public Optional<ENTITY> get(ID id) {
        return Optional.ofNullable(storedEntities.get(id));
    }

    @Override
    public void put(ID id, ENTITY entity) {
        storedEntities.put(id, entity);
    }

}
