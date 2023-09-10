package ovh.equino.actracker.searchfeed.application.tag;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record IndexTagCommand(UUID id,
                              boolean softDeleted,
                              long version,
                              UUID creatorId,
                              String name,
                              Map<UUID, Boolean> metricWithDeletedFlag,
                              Set<UUID> grantees) {
}
