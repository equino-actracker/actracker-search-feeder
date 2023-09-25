package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardProcessedNotifier;

//@Component
class AmqpDashboardProcessedNotifier extends AmqpEntityProcessedNotifier implements DashboardProcessedNotifier {

    @Override
    public void notifyProcessed(EntityProcessedNotification<DashboardId, Dashboard> entityProcessedNotification) {
        super.notifyEntityProcessed(entityProcessedNotification);
    }
}
