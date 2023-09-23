/*
 * This file is generated by jOOQ.
 */
package ovh.equino.actracker.searchfeed.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import ovh.equino.actracker.searchfeed.jooq.tables.ActivityTag;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivityTagRecord extends UpdatableRecordImpl<ActivityTagRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.activity_tag.activity_id</code>.
     */
    public void setActivityId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.activity_tag.activity_id</code>.
     */
    public String getActivityId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.activity_tag.tag_id</code>.
     */
    public void setTagId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.activity_tag.tag_id</code>.
     */
    public String getTagId() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return ActivityTag.ACTIVITY_TAG.ACTIVITY_ID;
    }

    @Override
    public Field<String> field2() {
        return ActivityTag.ACTIVITY_TAG.TAG_ID;
    }

    @Override
    public String component1() {
        return getActivityId();
    }

    @Override
    public String component2() {
        return getTagId();
    }

    @Override
    public String value1() {
        return getActivityId();
    }

    @Override
    public String value2() {
        return getTagId();
    }

    @Override
    public ActivityTagRecord value1(String value) {
        setActivityId(value);
        return this;
    }

    @Override
    public ActivityTagRecord value2(String value) {
        setTagId(value);
        return this;
    }

    @Override
    public ActivityTagRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActivityTagRecord
     */
    public ActivityTagRecord() {
        super(ActivityTag.ACTIVITY_TAG);
    }

    /**
     * Create a detached, initialised ActivityTagRecord
     */
    public ActivityTagRecord(String activityId, String tagId) {
        super(ActivityTag.ACTIVITY_TAG);

        setActivityId(activityId);
        setTagId(tagId);
    }
}
