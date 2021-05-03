CREATE TABLE `asset_assignment` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `assignment_id`    VARCHAR(64) NOT NULL,
    `asset_tag`    BIGINT(20) NOT NULL,
    `user_id`    BIGINT(20) NOT NULL,
    `assignment_date` DATE NOT NULL,
    `approved` BOOLEAN NOT NULL,
    PRIMARY KEY (`internal_id`),
    FOREIGN KEY `assignment_asset_fk` (`asset_tag`)  REFERENCES asset (`internal_id`) ON DELETE CASCADE,
    FOREIGN KEY `assignment_user_fk` (`user_id`)  REFERENCES user (`internal_id`) ON DELETE CASCADE
);