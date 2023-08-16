/*
 * This file is generated by jOOQ.
 */
package ovh.equino.actracker.searchfeed.jooq.tables.records;


import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import ovh.equino.actracker.searchfeed.jooq.tables.Activity;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivityRecord extends UpdatableRecordImpl<ActivityRecord> implements Record4<String, Long, Boolean, JSONB> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.activity.id</code>.
     */
    public ActivityRecord setId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.activity.id</code>.
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.activity.version</code>.
     */
    public ActivityRecord setVersion(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.activity.version</code>.
     */
    public Long getVersion() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.activity.deleted</code>.
     */
    public ActivityRecord setDeleted(Boolean value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.activity.deleted</code>.
     */
    public Boolean getDeleted() {
        return (Boolean) get(2);
    }

    /**
     * Setter for <code>public.activity.entity</code>.
     */
    public ActivityRecord setEntity(JSONB value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.activity.entity</code>.
     */
    public JSONB getEntity() {
        return (JSONB) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, Long, Boolean, JSONB> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<String, Long, Boolean, JSONB> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Activity.ACTIVITY.ID;
    }

    @Override
    public Field<Long> field2() {
        return Activity.ACTIVITY.VERSION;
    }

    @Override
    public Field<Boolean> field3() {
        return Activity.ACTIVITY.DELETED;
    }

    @Override
    public Field<JSONB> field4() {
        return Activity.ACTIVITY.ENTITY;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getVersion();
    }

    @Override
    public Boolean component3() {
        return getDeleted();
    }

    @Override
    public JSONB component4() {
        return getEntity();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getVersion();
    }

    @Override
    public Boolean value3() {
        return getDeleted();
    }

    @Override
    public JSONB value4() {
        return getEntity();
    }

    @Override
    public ActivityRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public ActivityRecord value2(Long value) {
        setVersion(value);
        return this;
    }

    @Override
    public ActivityRecord value3(Boolean value) {
        setDeleted(value);
        return this;
    }

    @Override
    public ActivityRecord value4(JSONB value) {
        setEntity(value);
        return this;
    }

    @Override
    public ActivityRecord values(String value1, Long value2, Boolean value3, JSONB value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActivityRecord
     */
    public ActivityRecord() {
        super(Activity.ACTIVITY);
    }

    /**
     * Create a detached, initialised ActivityRecord
     */
    public ActivityRecord(String id, Long version, Boolean deleted, JSONB entity) {
        super(Activity.ACTIVITY);

        setId(id);
        setVersion(version);
        setDeleted(deleted);
        setEntity(entity);
        resetChangedOnNotNull();
    }
}
