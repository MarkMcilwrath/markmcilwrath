-- Reference of OKTA Orgs we're interested in.
-- The customer_id is a public facing GUID
CREATE TABLE `user` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`     VARCHAR(64) NOT NULL,
    `firstname`            VARCHAR(100) NOT NULL,
    `lastname`   VARCHAR(255),
    `email` VARCHAR(255),
    `admin`   TINYINT(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`internal_id`),
    UNIQUE KEY `user_id` (`user_id`),
    UNIQUE KEY `email` (`email`)
);


CREATE TABLE `vendor` (
    `internal_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `vendor_id`     VARCHAR(64) NOT NULL,
    `vendor_name`            VARCHAR(100) NOT NULL,
    PRIMARY KEY (`internal_id`),
    UNIQUE KEY `vendor_name` (`vendor_name`)
);
