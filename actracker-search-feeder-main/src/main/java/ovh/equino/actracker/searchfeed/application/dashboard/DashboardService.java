package ovh.equino.actracker.searchfeed.application.dashboard;

import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.share.GranteeId;
import ovh.equino.actracker.searchfeed.domain.services.dashboard.DashboardProcessor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class DashboardService {

    private final DashboardProcessor dashboardProcessor;

    DashboardService(DashboardProcessor dashboardProcessor) {
        this.dashboardProcessor = dashboardProcessor;
    }

    public void indexDashboard(IndexDashboardCommand indexDashboardCommand) {
        Dashboard dashboard = new Dashboard(
                new DashboardId(indexDashboardCommand.id()),
                new Version(indexDashboardCommand.version()),
                indexDashboardCommand.softDeleted(),
                new CreatorId(indexDashboardCommand.creatorId()),
                indexDashboardCommand.name(),
                toGrantees(indexDashboardCommand.grantees())
        );

        dashboardProcessor.processDashboard(dashboard);
    }

    private Set<GranteeId> toGrantees(Set<UUID> grantees) {
        return requireNonNullElse(grantees, new HashSet<UUID>())
                .stream()
                .map(GranteeId::new)
                .collect(toUnmodifiableSet());
    }
}
