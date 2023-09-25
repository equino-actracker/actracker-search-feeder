package ovh.equino.actracker.searchfeed.main.springboot.config.refresh.amqp;

import org.springframework.stereotype.Component;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotifier;

//@Component
class AmqpTagSetRefreshedNotifier extends AmqpEntityRefreshedNotifier implements TagSetRefreshedNotifier {

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<TagSetId, TagSet> entityRefreshedNotification) {
        super.notifyEntityRefreshed(entityRefreshedNotification);
    }
}
