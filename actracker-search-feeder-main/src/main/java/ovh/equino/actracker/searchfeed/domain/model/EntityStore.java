package ovh.equino.actracker.searchfeed.domain.model;

import java.util.Optional;

public interface EntityStore<ID extends EntityId, ENTITY extends Entity<ID>> {

    Optional<ENTITY> get(ID id);

    void put(ID id, ENTITY entity);

}
