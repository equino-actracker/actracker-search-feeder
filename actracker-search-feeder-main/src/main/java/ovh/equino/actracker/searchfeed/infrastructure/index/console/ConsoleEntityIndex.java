package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.EntityGraph;
import ovh.equino.actracker.searchfeed.domain.model.EntityId;
import ovh.equino.actracker.searchfeed.domain.model.EntityIndex;

abstract class ConsoleEntityIndex<ID extends EntityId, GRAPH extends EntityGraph<ID>>
        implements EntityIndex<ID, GRAPH> {

    @Override
    public void index(GRAPH entityGraph) {
        String entityType = entityGraph.getClass().getSimpleName();
        System.out.printf("Indexed %s with ID %s%n", entityType, entityGraph.entityId());
    }

    @Override
    public void delete(ID id) {
        String entityIdType = id.getClass().getSimpleName();
        System.out.printf("Deleted entity graph with ID %s of type %s%n", id.id(), entityIdType);
    }
}
