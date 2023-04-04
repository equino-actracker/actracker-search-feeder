package ovh.equino.actracker.searchfeed.domain.model;

public abstract class Entity<Id extends EntityId> {

    private final Id id;
    private final Version version;
    private final boolean softDeleted;

    public Entity(Id id, Version version, boolean softDeleted) {
        this.id = id;
        this.version = version;
        this.softDeleted = softDeleted;
    }

    public Id id() {
        return id;
    }

    public boolean isSoftDeleted() {
        return softDeleted;
    }

    public boolean isNotDeleted() {
        return !isSoftDeleted();
    }

    public boolean shouldReplace(Entity<Id> otherEntity) {
        if (otherEntity == null) {
            return true;
        }
        return this.version.isLaterThan(otherEntity.version);
    }
}
