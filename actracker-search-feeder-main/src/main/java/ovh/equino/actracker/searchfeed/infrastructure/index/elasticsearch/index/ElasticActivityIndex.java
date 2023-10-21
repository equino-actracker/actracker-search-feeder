package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityGraph;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;

import java.time.Instant;
import java.util.Collection;

import static java.time.Instant.now;

public class ElasticActivityIndex extends ElasticIndex implements ActivityIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticActivityIndex.class);
    private static final String INDEX_NAME = "activity";

    public ElasticActivityIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }

    @Override
    public void index(ActivityGraph activityGraph) {
        ElasticActivityDocument document = new ElasticActivityDocument(
                activityGraph.entityId().toString(),
                activityGraph.activity().creatorId().toString(),
                now().toEpochMilli(),
                activityGraph.activity().title(),
                activityGraph.activity().startTime() != null ? activityGraph.activity().startTime().toEpochMilli() : null,
                activityGraph.activity().endTime() != null ? activityGraph.activity().endTime().toEpochMilli() : null,
                activityGraph.activity().comment(),
                null,
                null,
                null
        );
        super.indexDocument(document);
        LOG.info("Activity document with ID={} successfully indexed to Elasticsearch.", activityGraph.entityId().id());
    }

    @Override
    public void delete(ActivityId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("Activity document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private record ElasticActivityDocument(String id,
                                           String creator_id,
                                           Long indexing_time,
                                           String title,
                                           Long start_time,
                                           Long end_time,
                                           String comment,
                                           Collection<String> tags,
                                           Collection<String> grantees,
                                           Collection<ElasticMetricValueDocument> metric_values)
            implements ElasticDocument {
    }

    private record ElasticMetricValueDocument(String metric_id, String value) {
    }
}
