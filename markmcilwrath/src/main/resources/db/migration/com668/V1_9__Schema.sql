CREATE TABLE `asset_tag` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `asset_tag_id`    VARCHAR(64) NOT NULL,
    `tag_key` VARCHAR(64) NOT NULL,
    `tag_value` VARCHAR(64) NOT NULL,
    `assignment_id`    BIGINT(20) NOT NULL,
    PRIMARY KEY (`internal_id`),
    CONSTRAINT asset_tagKey UNIQUE (tag_key,assignment_id),
    FOREIGN KEY `asset_assignment_tag_fk` (`assignment_id`)  REFERENCES asset_assignment (`internal_id`) ON DELETE CASCADE
);