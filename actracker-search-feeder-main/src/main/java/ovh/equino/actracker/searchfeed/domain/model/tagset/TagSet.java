package ovh.equino.actracker.searchfeed.domain.model.tagset;

import com.fasterxml.jackson.annotation.JsonProperty;
import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.Version;
import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;

import java.util.Set;

public final class TagSet extends Entity<TagSetId> {

    private final String name;
    private final Set<TagId> tags;

    public TagSet(@JsonProperty("id") TagSetId id,
                  @JsonProperty("version") Version version,
                  @JsonProperty("softDeleted") boolean softDeleted,
                  @JsonProperty("creatorId") CreatorId creatorId,
                  @JsonProperty("name") String name,
                  @JsonProperty("tags") Set<TagId> tags) {

        super(id, version, softDeleted, creatorId);
        this.name = name;
        this.tags = tags;
    }

    public String name() {
        return name;
    }

    public Set<TagId> tags() {
        return tags;
    }
}
