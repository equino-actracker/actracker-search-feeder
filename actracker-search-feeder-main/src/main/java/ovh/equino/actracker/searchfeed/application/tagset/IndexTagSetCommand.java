package ovh.equino.actracker.searchfeed.application.tagset;

import java.util.Set;
import java.util.UUID;

public record IndexTagSetCommand(UUID id,
                                 boolean softDeleted,
                                 long version,
                                 UUID creatorId,
                                 String name,
                                 Set<UUID> tags) {
}
