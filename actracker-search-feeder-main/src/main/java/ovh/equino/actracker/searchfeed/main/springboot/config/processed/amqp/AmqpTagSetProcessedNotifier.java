package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetProcessedNotifier;

//@Component
class AmqpTagSetProcessedNotifier extends AmqpEntityProcessedNotifier implements TagSetProcessedNotifier {

    @Override
    public void notifyProcessed(EntityProcessedNotification<TagSetId, TagSet> entityProcessedNotification) {
        super.notifyEntityProcessed(entityProcessedNotification);
    }
}
