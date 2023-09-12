package ovh.equino.actracker.searchfeed.infrastructure.index.console;

import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardIndex;

final class ConsoleDashboardIndex extends ConsoleEntityIndex<DashboardId, Dashboard> implements DashboardIndex {
}
