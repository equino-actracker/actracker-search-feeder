package ovh.equino.actracker.searchfeed.domain.model;

public interface EntityIndex<ID extends EntityId, GRAPH extends EntityGraph<ID>> {

    void index(GRAPH entityGraph);

    void delete(ID id);

}
