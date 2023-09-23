CREATE TABLE IF NOT EXISTS activity_tag (
    activity_id VARCHAR(36),
    tag_id VARCHAR(36),
    PRIMARY KEY (activity_id, tag_id),
    CONSTRAINT activity_fk FOREIGN KEY (activity_id) REFERENCES activity(id),
    CONSTRAINT tag_fk FOREIGN KEY (tag_id) REFERENCES tag(id)
);

CREATE TABLE IF NOT EXISTS tagset_tag (
    tagset_id VARCHAR(36),
    tag_id VARCHAR(36),
    PRIMARY KEY (tagset_id, tag_id),
    CONSTRAINT tagset_fk FOREIGN KEY (tagset_id) REFERENCES tag_set(id),
    CONSTRAINT tag_fk FOREIGN KEY (tag_id) REFERENCES tag(id)
);
