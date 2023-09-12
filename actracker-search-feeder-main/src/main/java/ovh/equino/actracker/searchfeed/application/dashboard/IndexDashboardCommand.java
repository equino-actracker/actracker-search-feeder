package ovh.equino.actracker.searchfeed.application.dashboard;

import java.util.Set;
import java.util.UUID;

public record IndexDashboardCommand(UUID id,
                                    boolean softDeleted,
                                    long version,
                                    UUID creatorId,
                                    String name,
                                    Set<UUID> grantees) {
}
