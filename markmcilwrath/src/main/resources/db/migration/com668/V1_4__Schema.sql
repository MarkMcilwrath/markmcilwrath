CREATE TABLE `asset` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `asset_tag`    VARCHAR(64) NOT NULL,
    `serial_number` VARCHAR(64) NOT NULL,
    `purchase_date` DATE NOT NULL,
    `hardware_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`internal_id`),
    UNIQUE KEY `asset_tag` (`asset_tag`),
    CONSTRAINT tag_serial UNIQUE (asset_tag,serial_number),
    FOREIGN KEY `asset_hardware_fk` (`hardware_id`)  REFERENCES hardware (`internal_id`) ON DELETE CASCADE
);