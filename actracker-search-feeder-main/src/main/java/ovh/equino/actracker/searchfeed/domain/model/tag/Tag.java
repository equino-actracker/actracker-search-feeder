package ovh.equino.actracker.searchfeed.domain.model.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;
import ovh.equino.actracker.searchfeed.domain.model.share.GranteeId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toUnmodifiableSet;

public final class Tag extends Entity<TagId> {

    private final String name;
    private final Set<GranteeId> grantees;

    public Tag(@JsonProperty("id") TagId id,
               @JsonProperty("version") Version version,
               @JsonProperty("softDeleted") boolean softDeleted,
               @JsonProperty("creatorId") CreatorId creatorId,
               @JsonProperty("name") String name,
               @JsonProperty("grantees") Collection<GranteeId> grantees) {

        super(id, version, softDeleted, creatorId);
        this.name = name;
        this.grantees = requireNonNullElse(grantees, new ArrayList<GranteeId>())
                .stream()
                .collect(toUnmodifiableSet());
    }
}
