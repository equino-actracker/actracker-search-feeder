package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.Row2;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagId;
import ovh.equino.actracker.searchfeed.jooq.tables.records.ActivityRecord;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.JSONB.jsonb;
import static org.jooq.impl.DSL.row;
import static ovh.equino.actracker.searchfeed.jooq.Tables.ACTIVITY;
import static ovh.equino.actracker.searchfeed.jooq.Tables.ACTIVITY_TAG;

final class JooqActivityStore extends JooqEntityStore<ActivityId, Activity> implements ActivityStore {

    private final DSLContext jooq;

    JooqActivityStore(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public Optional<Activity> get(ActivityId id) {
        Optional<ActivityRecord> activityRecord = jooq.selectFrom(ACTIVITY)
                .where(ACTIVITY.ID.equal(id.toString()))
                .fetchOptional();
        return activityRecord.map(record -> deserialize(
                record.get(ACTIVITY.ENTITY), Activity.class)
        );
    }

    @Override
    public void put(ActivityId id, Activity entity) {
        jooq.deleteFrom(ACTIVITY_TAG)
                .where(ACTIVITY_TAG.ACTIVITY_ID.equal(id.toString()))
                .execute();

        JSONB serializedEntity = jsonb(serialize(entity));
        jooq.insertInto(ACTIVITY)
                .columns(ACTIVITY.ID, ACTIVITY.VERSION, ACTIVITY.DELETED, ACTIVITY.ENTITY)
                .values(entity.id().toString(), entity.version().version(), entity.isSoftDeleted(), serializedEntity)
                .onConflict()
                .doUpdate()
                .set(ACTIVITY.VERSION, entity.version().version())
                .set(ACTIVITY.DELETED, entity.isSoftDeleted())
                .set(ACTIVITY.ENTITY, serializedEntity)
                .execute();

        List<Row2<String, String>> activityTagIds = entity.tags().stream()
                .map(tagId -> row(id.toString(), tagId.toString()))
                .toList();
        jooq.insertInto(ACTIVITY_TAG)
                .columns(ACTIVITY_TAG.ACTIVITY_ID, ACTIVITY_TAG.TAG_ID)
                .valuesOfRows(activityTagIds)
                .execute();
    }

    @Override
    public Collection<ActivityId> findByTag(TagId tagId) {
        List<String> activityIds = jooq.selectFrom(ACTIVITY_TAG)
                .where(ACTIVITY_TAG.TAG_ID.equal(tagId.toString()))
                .fetch(ACTIVITY_TAG.ACTIVITY_ID);

        return activityIds
                .stream()
                .map(UUID::fromString)
                .map(ActivityId::new)
                .toList();
    }
}
