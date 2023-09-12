package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;
import ovh.equino.actracker.searchfeed.jooq.tables.records.TagSetRecord;

import java.util.Optional;

import static org.jooq.JSONB.jsonb;
import static ovh.equino.actracker.searchfeed.jooq.Tables.TAG_SET;

final class JooqTagSetStore extends JooqEntityStore<TagSetId, TagSet> implements TagSetStore {

    private final DSLContext jooq;

    JooqTagSetStore(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public Optional<TagSet> get(TagSetId id) {
        Optional<TagSetRecord> tagSetRecord = jooq.selectFrom(TAG_SET)
                .where(TAG_SET.ID.equal(id.toString()))
                .fetchOptional();
        return tagSetRecord.map(record -> deserialize(
                record.get(TAG_SET.ENTITY), TagSet.class)
        );
    }

    @Override
    public void put(TagSetId id, TagSet entity) {
        JSONB serializedEntity = jsonb(serialize(entity));
        jooq.insertInto(TAG_SET)
                .columns(TAG_SET.ID, TAG_SET.VERSION, TAG_SET.DELETED, TAG_SET.ENTITY)
                .values(entity.id().toString(), entity.version().version(), entity.isSoftDeleted(), serializedEntity)
                .onConflict()
                .doUpdate()
                .set(TAG_SET.VERSION, entity.version().version())
                .set(TAG_SET.DELETED, entity.isSoftDeleted())
                .set(TAG_SET.ENTITY, serializedEntity)
                .execute();
    }
}
