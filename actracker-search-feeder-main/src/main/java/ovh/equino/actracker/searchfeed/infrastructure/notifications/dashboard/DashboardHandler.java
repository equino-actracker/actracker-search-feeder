package ovh.equino.actracker.searchfeed.infrastructure.notifications.dashboard;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.dashboard.DashboardChangedNotification;
import ovh.equino.actracker.domain.share.Share;
import ovh.equino.actracker.domain.user.User;
import ovh.equino.actracker.searchfeed.application.dashboard.DashboardService;
import ovh.equino.actracker.searchfeed.application.dashboard.IndexDashboardCommand;
import ovh.equino.actracker.searchfeed.infrastructure.notifications.NotificationHandler;

import java.util.*;

import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toUnmodifiableSet;

class DashboardHandler implements NotificationHandler<DashboardChangedNotification> {

    private final DashboardService dashboardService;

    DashboardHandler(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Override
    public void handleNotification(Notification<?> dashboardChangedNotification) {

        Notification<DashboardChangedNotification> notification = (Notification<DashboardChangedNotification>) dashboardChangedNotification;

        IndexDashboardCommand indexDashboardCommand = new IndexDashboardCommand(
                notification.id(),
                notification.data().dashboard().deleted(),
                notification.version(),
                notification.data().dashboard().creatorId(),
                notification.data().dashboard().name(),
                toGrantees(notification.data().dashboard().shares())
        );

        dashboardService.indexDashboard(indexDashboardCommand);
    }

    @Override
    public Class<DashboardChangedNotification> supportedNotificationType() {
        return DashboardChangedNotification.class;
    }

    private Set<UUID> toGrantees(Collection<Share> shares) {
        return requireNonNullElse(shares, new ArrayList<Share>())
                .stream()
                .map(Share::grantee)
                .filter(Objects::nonNull)
                .map(User::id)
                .collect(toUnmodifiableSet());
    }
}
