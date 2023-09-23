package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.Row2;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSet;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetId;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetStore;
import ovh.equino.actracker.searchfeed.jooq.tables.records.TagSetRecord;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.JSONB.jsonb;
import static org.jooq.impl.DSL.row;
import static ovh.equino.actracker.searchfeed.jooq.Tables.TAGSET_TAG;
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
        jooq.deleteFrom(TAGSET_TAG)
                .where(TAGSET_TAG.TAGSET_ID.equal(id.toString()))
                .execute();

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

        List<Row2<String, String>> tagsetTagIds = entity.tags().stream()
                .map(tagId -> row(id.toString(), tagId.toString()))
                .toList();
        jooq.insertInto(TAGSET_TAG)
                .columns(TAGSET_TAG.TAGSET_ID, TAGSET_TAG.TAG_ID)
                .valuesOfRows(tagsetTagIds)
                .execute();
    }

    @Override
    public Collection<TagSetId> findByTag(TagId tagId) {
        List<String> tagSetIds = jooq.selectFrom(TAGSET_TAG)
                .where(TAGSET_TAG.TAG_ID.equal(tagId.toString()))
                .fetch(TAGSET_TAG.TAGSET_ID);

        return tagSetIds
                .stream()
                .map(UUID::fromString)
                .map(TagSetId::new)
                .toList();
    }
}
