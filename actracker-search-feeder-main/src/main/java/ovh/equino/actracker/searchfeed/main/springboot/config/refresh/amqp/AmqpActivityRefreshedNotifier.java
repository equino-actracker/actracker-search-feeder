package ovh.equino.actracker.searchfeed.main.springboot.config.refresh.amqp;

import org.springframework.stereotype.Component;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityRefreshedNotifier;

//@Component
class AmqpActivityRefreshedNotifier extends AmqpEntityRefreshedNotifier implements ActivityRefreshedNotifier {

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<ActivityId, Activity> entityRefreshedNotification) {
        super.notifyEntityRefreshed(entityRefreshedNotification);
    }
}
