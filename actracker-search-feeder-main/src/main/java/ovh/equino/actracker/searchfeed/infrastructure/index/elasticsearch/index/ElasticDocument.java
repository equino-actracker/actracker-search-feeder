package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

interface ElasticDocument {

    String id();

    String creator_id();

    Long indexing_time();
}
