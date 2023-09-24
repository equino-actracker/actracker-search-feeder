package ovh.equino.actracker.searchfeed.infrastructure.refresh.direct;

import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetRefreshedNotifier;

class DirectTagSetRefreshedNotifier implements TagSetRefreshedNotifier {

    private final DirectTagSetRefreshedNotificationHandler notificationHandler;

    DirectTagSetRefreshedNotifier(DirectTagSetRefreshedNotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void notifyRefreshed(TagSetId tagSetId) {
        notificationHandler.refreshedNotificationReceived(tagSetId);
    }
}
