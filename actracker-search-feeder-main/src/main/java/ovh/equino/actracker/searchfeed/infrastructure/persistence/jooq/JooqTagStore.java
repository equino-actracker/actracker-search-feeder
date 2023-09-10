package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import ovh.equino.actracker.searchfeed.domain.model.tag.Tag;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagStore;
import ovh.equino.actracker.searchfeed.jooq.tables.records.TagRecord;

import java.util.Optional;

import static org.jooq.JSONB.jsonb;
import static ovh.equino.actracker.searchfeed.jooq.Tables.TAG;

final class JooqTagStore extends JooqEntityStore<TagId, Tag> implements TagStore {

    private final DSLContext jooq;

    JooqTagStore(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public Optional<Tag> get(TagId id) {
        Optional<TagRecord> activityRecord = jooq.selectFrom(TAG)
                .where(TAG.ID.equal(id.toString()))
                .fetchOptional();
        return activityRecord.map(record -> deserialize(
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
}
