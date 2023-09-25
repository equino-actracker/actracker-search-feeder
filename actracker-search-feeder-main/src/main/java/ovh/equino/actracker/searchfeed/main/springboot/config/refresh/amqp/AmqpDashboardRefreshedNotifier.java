package ovh.equino.actracker.searchfeed.main.springboot.config.refresh.amqp;

import org.springframework.stereotype.Component;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardRefreshedNotifier;

//@Component
class AmqpDashboardRefreshedNotifier extends AmqpEntityRefreshedNotifier implements DashboardRefreshedNotifier {

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<DashboardId, Dashboard> entityRefreshedNotification) {
        super.notifyEntityRefreshed(entityRefreshedNotification);
    }
}
