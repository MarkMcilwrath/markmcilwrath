CREATE TABLE `license_assignment` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `assignment_id`    VARCHAR(64) NOT NULL,
    `license_key`    BIGINT(20) NOT NULL,
    `user_id`    BIGINT(20) NOT NULL,
    `assignment_date` DATE NOT NULL,
    `approved` BOOLEAN NOT NULL,
    PRIMARY KEY (`internal_id`),
    FOREIGN KEY `assignment_license_fk` (`license_key`)  REFERENCES license (`internal_id`) ON DELETE CASCADE,
    FOREIGN KEY `assignment_user_fk` (`user_id`)  REFERENCES user (`internal_id`) ON DELETE CASCADE
);