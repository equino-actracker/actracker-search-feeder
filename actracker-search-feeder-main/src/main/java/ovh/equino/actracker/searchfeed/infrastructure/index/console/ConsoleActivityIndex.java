package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityGraph;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;

final class ConsoleActivityIndex extends ConsoleEntityIndex<ActivityId, ActivityGraph> implements ActivityIndex {
}
