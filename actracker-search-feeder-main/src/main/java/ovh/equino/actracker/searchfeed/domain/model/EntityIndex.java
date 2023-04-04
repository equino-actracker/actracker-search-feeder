package ovh.equino.actracker.searchfeed.domain.model;

public interface EntityIndex<Id extends EntityId, E extends Entity<Id>> {

    void index(E entity);

    void delete(Id id);

}
