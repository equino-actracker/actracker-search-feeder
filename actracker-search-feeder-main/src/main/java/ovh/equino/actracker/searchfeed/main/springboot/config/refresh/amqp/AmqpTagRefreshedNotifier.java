package ovh.equino.actracker.searchfeed.main.springboot.config.refresh.amqp;

import org.springframework.stereotype.Component;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagRefreshedNotifier;

//@Component
class AmqpTagRefreshedNotifier extends AmqpEntityRefreshedNotifier implements TagRefreshedNotifier {

    @Override
    public void notifyRefreshed(EntityRefreshedNotification<TagId, Tag> entityRefreshedNotification) {
        super.notifyEntityRefreshed(entityRefreshedNotification);
    }
}
