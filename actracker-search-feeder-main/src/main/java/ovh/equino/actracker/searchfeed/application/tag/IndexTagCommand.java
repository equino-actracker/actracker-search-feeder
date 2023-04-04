package ovh.equino.actracker.searchfeed.application.tag;

import java.util.UUID;

public record IndexTagCommand(

        UUID id,
        boolean softDeleted,
        long version,
        String name

) {
}
