package ovh.equino.actracker.searchfeed.infrastructure.messaging.tag;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.tag.TagChangedNotification;
import ovh.equino.actracker.searchfeed.application.tag.IndexTagCommand;
import ovh.equino.actracker.searchfeed.application.tag.TagService;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.NotificationHandler;

class TagHandler implements NotificationHandler<TagChangedNotification> {

    private final TagService tagService;

    TagHandler(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public void handleNotification(Notification<?> tagChangedNotification) {

        Notification<TagChangedNotification> notification = (Notification<TagChangedNotification>) tagChangedNotification;

        IndexTagCommand indexTagCommand = new IndexTagCommand(
                notification.id(),
                notification.data().tag().deleted(),
                notification.version(),
                notification.data().tag().name()
        );

        tagService.indexTag(indexTagCommand);
    }

    @Override
    public Class<TagChangedNotification> supportedNotificationType() {
        return TagChangedNotification.class;
    }
}
