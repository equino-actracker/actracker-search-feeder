package ovh.equino.actracker.searchfeed.domain.model;

public abstract class Entity<ID extends EntityId> {

    private final ID id;
    private final Version version;
    private final boolean softDeleted;

    public Entity(ID id, Version version, boolean softDeleted) {
        this.id = id;
        this.version = version;
        this.softDeleted = softDeleted;
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

    public boolean shouldReplace(Entity<ID> otherEntity) {
        if (otherEntity == null) {
            return true;
        }
        return this.version.isLaterThan(otherEntity.version);
    }
}
