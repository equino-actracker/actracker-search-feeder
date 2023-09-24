package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagRefreshedNotifier;

class DirectTagRefreshedNotifier implements TagRefreshedNotifier {

    private final DirectTagRefreshedNotificationHandler notificationHandler;

    DirectTagRefreshedNotifier(DirectTagRefreshedNotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void notifyRefreshed(TagId tagId) {
        notificationHandler.refreshedNotificationReceived(tagId);
    }
}
