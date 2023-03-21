package ovh.equino.actracker.searchfeed.infrastructure.messaging;

public interface NotificationHandler<T> {

    void handleNotification(T notification);
}
