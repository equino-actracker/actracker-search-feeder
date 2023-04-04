package ovh.equino.actracker.searchfeed.domain.model;

import java.util.Objects;
import java.util.UUID;

public abstract class EntityId {

    private final UUID id;

    protected EntityId(UUID id) {
        this.id = Objects.requireNonNull(id);
    }

    public UUID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityId entityId = (EntityId) o;
        return Objects.equals(id, entityId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
