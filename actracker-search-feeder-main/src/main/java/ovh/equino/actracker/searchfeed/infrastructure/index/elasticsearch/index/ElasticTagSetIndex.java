package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetGraph;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetIndex;

public class ElasticTagSetIndex extends ElasticIndex implements TagSetIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticTagSetIndex.class);
    private static final String INDEX_NAME = "tagset";

    public ElasticTagSetIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(TagSetGraph entityGraph) {
        ElasticTagSetDocument document = new ElasticTagSetDocument(entityGraph.entityId().id().toString());
        super.indexDocument(document);
        LOG.info("TagSet document with ID={} successfully indexed to Elasticsearch.", entityGraph.entityId().id());
    }

    @Override
    public void delete(TagSetId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("TagSet document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private record ElasticTagSetDocument(String id) implements ElasticDocument {
    }
}
