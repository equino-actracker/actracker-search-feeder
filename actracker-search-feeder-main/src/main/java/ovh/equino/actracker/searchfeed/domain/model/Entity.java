package ovh.equino.actracker.searchfeed.domain.model;

import ovh.equino.actracker.searchfeed.domain.model.creator.CreatorId;

public abstract class Entity<ID extends EntityId> {

    private final ID id;
    private final Version version;
    private final boolean softDeleted;
    private final CreatorId creatorId;

    public Entity(ID id, Version version, boolean softDeleted, CreatorId creatorId) {
        this.id = id;
        this.version = version;
        this.softDeleted = softDeleted;
        this.creatorId = creatorId;
    }

    public ID id() {
        return id;
    }

    public boolean isSoftDeleted() {
        return softDeleted;
    }

    public boolean isNotDeleted() {
        return !isSoftDeleted();
    }

    public Version version() {
        return version;
    }

    public CreatorId creatorId() {
        return creatorId;
    }

    public boolean shouldReplace(Entity<ID> otherEntity) {
        if (otherEntity == null) {
            return true;
        }
        return this.version.isLaterThan(otherEntity.version);
    }
}
