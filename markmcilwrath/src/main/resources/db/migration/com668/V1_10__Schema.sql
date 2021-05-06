CREATE TABLE `license_archive` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `license_key`    VARCHAR(64) NOT NULL,
    `purchase_date` DATE NOT NULL,
    `expiry_date` DATE,
    `software_id` BIGINT(64) NOT NULL,
    `software_name`    VARCHAR(64) NOT NULL,
    `software_version`    VARCHAR(64) NOT NULL,
    PRIMARY KEY (`internal_id`)
);