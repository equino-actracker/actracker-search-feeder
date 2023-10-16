package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagGraph;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagIndex;

public class ElasticTagIndex extends ElasticIndex implements TagIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticTagIndex.class);
    private static final String INDEX_NAME = "tag";

    public ElasticTagIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(TagGraph entityGraph) {
        LOG.info("Indexing tag document with ID={} to Elasticsearch", entityGraph.entityId().id());
    }

    @Override
    public void delete(TagId id) {
        LOG.info("Deleting tag document with ID={} from Elasticsearch", id.id());
    }
}
