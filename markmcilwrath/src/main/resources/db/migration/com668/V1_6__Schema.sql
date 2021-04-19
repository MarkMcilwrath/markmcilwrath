CREATE TABLE `license_assignment` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `assignment_id`    VARCHAR(64) NOT NULL,
    `license_key`    VARCHAR(64) NOT NULL,
    `user_id`    VARCHAR(64) NOT NULL,
    `assignment_date` DATE NOT NULL,
    `approved` BOOLEAN NOT NULL,
    PRIMARY KEY (`internal_id`)
);