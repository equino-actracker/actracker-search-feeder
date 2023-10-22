package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityGraph;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;
import ovh.equino.actracker.searchfeed.domain.model.metricValue.MetricValue;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import static java.time.Instant.now;
import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

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
                toTagIds(activityGraph.tags()),
                toGranteeIds(activityGraph.tags()),
                toMetricValues(activityGraph.activity().metricValues())
        );
        super.indexDocument(document);
        LOG.info("Activity document with ID={} successfully indexed to Elasticsearch.", activityGraph.entityId().id());
    }

    @Override
    public void delete(ActivityId id) {
        super.deleteDocument(id.id().toString());
        LOG.info("Activity document with ID={} successfully deleted from Elasticsearch.", id.id());
    }

    private Collection<String> toTagIds(Collection<Tag> tags) {
        if (isEmpty(tags)) {
            return null;
        }
        return tags
                .stream()
                .map(tag -> tag.id().toString())
                .collect(toUnmodifiableSet());
    }

    private Collection<String> toGranteeIds(Collection<Tag> tags) {
        Set<String> granteeIds = requireNonNullElse(tags, new ArrayList<Tag>())
                .stream()
                .map(Tag::grantees)
                .flatMap(Collection::stream)
                .map(Objects::toString)
                .collect(toUnmodifiableSet());
        return isNotEmpty(granteeIds) ? granteeIds : null;
    }

    private Collection<ElasticMetricValueDocument> toMetricValues(Collection<MetricValue> metricValues) {
        if (isEmpty(metricValues)) {
            return null;
        }
        return metricValues
                .stream()
                .map(metricValue -> new ElasticMetricValueDocument(metricValue.metricId().toString(), metricValue.value()))
                .toList();
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

    private record ElasticMetricValueDocument(String metric_id, BigDecimal value) {
    }
}
