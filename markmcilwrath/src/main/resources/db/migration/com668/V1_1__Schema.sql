CREATE TABLE `software` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `software_id`     VARCHAR(64) NOT NULL,
    `name`            VARCHAR(64) NOT NULL,
    `version`         VARCHAR(64) NOT NULL,
    `vendor_id`       VARCHAR(64) NOT NULL,
    PRIMARY KEY (`internal_id`),
    UNIQUE KEY `software_id` (`software_id`),
    CONSTRAINT name_version UNIQUE (name,version)

);