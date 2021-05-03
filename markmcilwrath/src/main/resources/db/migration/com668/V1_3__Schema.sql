CREATE TABLE `license` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `license_key`    VARCHAR(64) NOT NULL,
    `purchase_date` DATE NOT NULL,
    `expiry_date` DATE,
    `software_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`internal_id`),
    UNIQUE KEY `license_key` (`license_key`),
    CONSTRAINT license_software UNIQUE (license_key,software_id),
    FOREIGN KEY `software_license_fk` (`software_id`)  REFERENCES software (`internal_id`) ON DELETE CASCADE
);