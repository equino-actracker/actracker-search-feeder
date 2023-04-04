package ovh.equino.actracker.searchfeed.domain.model;

public interface EntityIndex<ID extends EntityId, ENTITY extends Entity<ID>> {

    void index(ENTITY entity);

    void delete(ID id);

}
