package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.jooq.tables.records.TagRecord;

import java.util.*;

import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.jooq.JSONB.jsonb;
import static org.jooq.impl.DSL.inline;
import static ovh.equino.actracker.searchfeed.jooq.Tables.TAG;

final class JooqTagStore extends JooqEntityStore<TagId, Tag> implements TagStore {

    private final DSLContext jooq;

    JooqTagStore(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public Optional<Tag> get(TagId id) {
        Optional<TagRecord> tagRecord = jooq.selectFrom(TAG)
                .where(TAG.ID.equal(id.toString()))
                .fetchOptional();
        return tagRecord.map(record -> deserialize(
                record.get(TAG.ENTITY), Tag.class)
        );
    }

    @Override
    public void put(TagId id, Tag entity) {
        JSONB serializedEntity = jsonb(serialize(entity));
        jooq.insertInto(TAG)
                .columns(TAG.ID, TAG.VERSION, TAG.DELETED, TAG.ENTITY)
                .values(entity.id().toString(), entity.version().version(), entity.isSoftDeleted(), serializedEntity)
                .onConflict()
                .doUpdate()
                .set(TAG.VERSION, entity.version().version())
                .set(TAG.DELETED, entity.isSoftDeleted())
                .set(TAG.ENTITY, serializedEntity)
                .execute();
    }

    @Override
    public Set<TagId> nonDeletedTags(Collection<TagId> tagIds) {
        Set<String> tagIdStrings = requireNonNullElse(tagIds, new ArrayList<>())
                .stream()
                .map(Object::toString)
                .collect(toUnmodifiableSet());

        Set<String> nonDeletedTagIds = jooq.selectFrom(TAG)
                .where(TAG.ID.in(tagIdStrings))
                .and(TAG.DELETED.eq(inline(false)))
                .fetchSet(TAG.ID);

        return nonDeletedTagIds.stream()
                .map(UUID::fromString)
                .map(TagId::new)
                .collect(toUnmodifiableSet());
    }
}
