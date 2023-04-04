package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;

final class ConsoleActivityIndex extends ConsoleEntityIndex<ActivityId, Activity> implements ActivityIndex {
}
