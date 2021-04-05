CREATE TABLE `hardware` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `hardware_id`     VARCHAR(64) NOT NULL,
    `name`            VARCHAR(64) NOT NULL,
    `model`         VARCHAR(64) NOT NULL,
    PRIMARY KEY (`internal_id`),
    UNIQUE KEY `hardware_id` (`hardware_id`)
);