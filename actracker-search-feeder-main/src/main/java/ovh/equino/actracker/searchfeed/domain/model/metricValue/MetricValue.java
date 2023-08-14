package ovh.equino.actracker.searchfeed.domain.model.metricValue;

import ovh.equino.actracker.searchfeed.domain.model.metric.MetricId;

import java.math.BigDecimal;

public record MetricValue(MetricId metricId,
                          BigDecimal value) {
}
