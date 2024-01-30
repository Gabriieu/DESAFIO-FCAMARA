CREATE DATABASE IF NOT EXISTS `estacionamento` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;
USE `estacionamento`;

CREATE TABLE IF NOT EXISTS `companies`
(
    `id`                bigint       NOT NULL AUTO_INCREMENT,
    `address`           varchar(255) NOT NULL,
    `cars_limit`        int          NOT NULL,
    `cnpj`              varchar(255) NOT NULL,
    `motorcycles_limit` int          NOT NULL,
    `name`              varchar(255) NOT NULL,
    `phone`             varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_1phmrrhw2946lhdeh2yjis697` (`cnpj`),
    UNIQUE KEY `UK_dhwrttsjdtx8826arlcdna6v4` (`phone`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flyway_schema_history`
(
    `installed_rank` int           NOT NULL,
    `version`        varchar(50)            DEFAULT NULL,
    `description`    varchar(200)  NOT NULL,
    `type`           varchar(20)   NOT NULL,
    `script`         varchar(1000) NOT NULL,
    `checksum`       int                    DEFAULT NULL,
    `installed_by`   varchar(100)  NOT NULL,
    `installed_on`   timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `execution_time` int           NOT NULL,
    `success`        tinyint(1)    NOT NULL,
    PRIMARY KEY (`installed_rank`),
    KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `vehicles`
(
    `id`            bigint                    NOT NULL AUTO_INCREMENT,
    `brand`         varchar(255)              NOT NULL,
    `color`         varchar(255)              NOT NULL,
    `license_plate` varchar(255)              NOT NULL,
    `model`         varchar(255)              NOT NULL,
    `type`          enum ('CAR','MOTORCYCLE') NOT NULL,
    `company_id`    bigint                    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_9vovnbiegxevdhqfcwvp2g8pj` (`license_plate`),
    KEY `FKwvkljwybf9jpcarv5iclh6yo` (`company_id`),
    CONSTRAINT `FKwvkljwybf9jpcarv5iclh6yo` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

