package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import ovh.equino.actracker.searchfeed.domain.model.activity.Activity;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityId;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityStore;
import ovh.equino.actracker.searchfeed.jooq.tables.records.ActivityRecord;

import java.util.Optional;

import static org.jooq.JSONB.jsonb;
import static ovh.equino.actracker.searchfeed.jooq.Tables.ACTIVITY;

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
    }
}
