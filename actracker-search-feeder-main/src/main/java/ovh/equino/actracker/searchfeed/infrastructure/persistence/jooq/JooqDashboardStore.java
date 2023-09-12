package ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.Dashboard;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardId;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardStore;
import ovh.equino.actracker.searchfeed.jooq.tables.records.DashboardRecord;

import java.util.Optional;

import static org.jooq.JSONB.jsonb;
import static ovh.equino.actracker.searchfeed.jooq.Tables.DASHBOARD;

final class JooqDashboardStore extends JooqEntityStore<DashboardId, Dashboard> implements DashboardStore {

    private final DSLContext jooq;

    JooqDashboardStore(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public Optional<Dashboard> get(DashboardId id) {
        Optional<DashboardRecord> dashboardRecord = jooq.selectFrom(DASHBOARD)
                .where(DASHBOARD.ID.equal(id.toString()))
                .fetchOptional();
        return dashboardRecord.map(record -> deserialize(
                record.get(DASHBOARD.ENTITY), Dashboard.class)
        );
    }

    @Override
    public void put(DashboardId id, Dashboard entity) {
        JSONB serializedEntity = jsonb(serialize(entity));
        jooq.insertInto(DASHBOARD)
                .columns(DASHBOARD.ID, DASHBOARD.VERSION, DASHBOARD.DELETED, DASHBOARD.ENTITY)
                .values(entity.id().toString(), entity.version().version(), entity.isSoftDeleted(), serializedEntity)
                .onConflict()
                .doUpdate()
                .set(DASHBOARD.VERSION, entity.version().version())
                .set(DASHBOARD.DELETED, entity.isSoftDeleted())
                .set(DASHBOARD.ENTITY, serializedEntity)
                .execute();
    }
}
