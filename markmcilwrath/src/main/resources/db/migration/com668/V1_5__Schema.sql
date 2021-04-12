CREATE TABLE `license_tag` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `license_key`    VARCHAR(64) NOT NULL,
    `tag` VARCHAR(64) NOT NULL,
    `value` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`internal_id`),
    CONSTRAINT license_tag UNIQUE (license_key,tag)
);