package ovh.equino.actracker.searchfeed.domain.model.tag;

import ovh.equino.actracker.searchfeed.domain.model.Entity;
import ovh.equino.actracker.searchfeed.domain.model.Version;

public final class Tag extends Entity<TagId> {

    private final String name;

    public Tag(TagId id, Version version, boolean softDeleted, String name) {
        super(id, version, softDeleted);
        this.name = name;
    }
}
