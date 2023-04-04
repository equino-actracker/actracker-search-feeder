package ovh.equino.actracker.searchfeed.domain.model;

import java.util.Optional;

public interface EntityStore<Id extends EntityId, E extends Entity<Id>> {

    Optional<E> get(Id id);

    void put(Id id, E entity);

}
