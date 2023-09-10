package ovh.equino.actracker.searchfeed.infrastructure.messaging.tag;

import ovh.equino.actracker.domain.Notification;
import ovh.equino.actracker.domain.share.Share;
import ovh.equino.actracker.domain.tag.MetricDto;
import ovh.equino.actracker.domain.tag.TagChangedNotification;
import ovh.equino.actracker.domain.user.User;
import ovh.equino.actracker.searchfeed.application.tag.IndexTagCommand;
import ovh.equino.actracker.searchfeed.application.tag.TagService;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.NotificationHandler;

import java.util.*;

import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toUnmodifiableSet;

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
                notification.data().tag().creatorId(),
                notification.data().tag().name(),
                toMetricsMap(notification.data().tag().metrics()),
                toGrantees(notification.data().tag().shares())
        );

        tagService.indexTag(indexTagCommand);
    }

    @Override
    public Class<TagChangedNotification> supportedNotificationType() {
        return TagChangedNotification.class;
    }

    private Map<UUID, Boolean> toMetricsMap(Collection<MetricDto> metrics) {
        return requireNonNullElse(metrics, new ArrayList<MetricDto>())
                .stream()
                .collect(toMap(MetricDto::id, MetricDto::deleted));
    }

    private Set<UUID> toGrantees(Collection<Share> shares) {
        return requireNonNullElse(shares, new ArrayList<Share>())
                .stream()
                .map(Share::grantee)
                .filter(Objects::nonNull)
                .map(User::id)
                .collect(toUnmodifiableSet());
    }
}
