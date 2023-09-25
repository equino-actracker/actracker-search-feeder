package ovh.equino.actracker.searchfeed.infrastructure.notifications.tagset;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.tagset.TagSetChangedNotification;
import ovh.equino.actracker.searchfeed.application.tagset.IndexTagSetCommand;
import ovh.equino.actracker.searchfeed.application.tagset.TagSetService;
import ovh.equino.actracker.searchfeed.infrastructure.notifications.NotificationHandler;

class TagSetHandler implements NotificationHandler<TagSetChangedNotification> {

    private final TagSetService tagSetService;

    TagSetHandler(TagSetService tagSetService) {
        this.tagSetService = tagSetService;
    }

    @Override
    public void handleNotification(Notification<?> tagSetChangedNotification) {

        Notification<TagSetChangedNotification> notification = (Notification<TagSetChangedNotification>) tagSetChangedNotification;

        IndexTagSetCommand indexTagSetCommand = new IndexTagSetCommand(
                notification.id(),
                notification.data().tagSet().deleted(),
                notification.version(),
                notification.data().tagSet().creatorId(),
                notification.data().tagSet().name(),
                notification.data().tagSet().tags()
        );

        tagSetService.indexTagSet(indexTagSetCommand);
    }

    @Override
    public Class<TagSetChangedNotification> supportedNotificationType() {
        return TagSetChangedNotification.class;
    }
}
