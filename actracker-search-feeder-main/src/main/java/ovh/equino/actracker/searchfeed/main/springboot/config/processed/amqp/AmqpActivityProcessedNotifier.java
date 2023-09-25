package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityProcessedNotifier;

//@Component
class AmqpActivityProcessedNotifier extends AmqpEntityProcessedNotifier implements ActivityProcessedNotifier {

    @Override
    public void notifyProcessed(EntityProcessedNotification<ActivityId, Activity> entityProcessedNotification) {
        super.notifyEntityProcessed(entityProcessedNotification);
    }
}
