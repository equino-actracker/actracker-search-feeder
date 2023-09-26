package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import org.springframework.stereotype.Component;
import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagProcessedNotifier;

@Component
class AmqpTagProcessedNotifier extends AmqpEntityProcessedNotifier implements TagProcessedNotifier {

    @Override
    public void notifyProcessed(EntityProcessedNotification<TagId, Tag> entityProcessedNotification) {
        super.notifyEntityProcessed(entityProcessedNotification);
    }
}
