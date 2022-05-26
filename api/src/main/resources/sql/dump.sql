SET NAMES utf8;
SET foreign_key_checks = 0;

DROP DATABASE IF EXISTS `taxi_depot`;
CREATE DATABASE `taxi_depot` character set utf8 collate utf8_general_ci;
USE `taxi_depot`;

CREATE TABLE `car`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `brand`         varchar(100) NOT NULL,
    `cost`          int(11)      NOT NULL,
    `equipment`     varchar(50)  NOT NULL,
    `consumption`   tinyint(4)   NOT NULL,
    `model`         varchar(100) NOT NULL,
    `number`        varchar(255) NOT NULL,
    `year`          smallint(6)  NOT NULL,
    `date_creation` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `driver`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `last_name`     varchar(100) NOT NULL,
    `name`          varchar(100) NOT NULL,
    `middle_name`   varchar(100) NOT NULL,
    `birth_date`    date         NOT NULL,
    `licence`       varchar(100) NOT NULL,
    `date_creation` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `car_driver`
(
    `driver_id` bigint(20) NOT NULL,
    `car_id`    bigint(20) NOT NULL,
    KEY `car_driver_ibfk_1` (`car_id`),
    KEY `car_driver_ibfk_2` (`driver_id`),
    CONSTRAINT `car_driver_ibfk_1` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
    CONSTRAINT `car_driver_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `car` (`id`, `brand`, `cost`, `equipment`, `consumption`, `model`, `number`, `year`, `date_creation`,
                   `last_modified`)
VALUES (1, 'Volkswagen', 5000, 'standart', 5, 'Polo', '2536 AE-3', 2005, '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (2, 'Reno', 4500, 'standart', 5, 'Logan', '1458 IH-3', 2007, '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (3, 'Volkswagen', 5000, 'economy', 5, 'Polo', '5894 EE-3', 2005, '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (4, 'Volkswagen', 7000, 'standart', 7, 'Passat', '4579 II-3', 2009, '2022-05-22 11:53:49',
        '2022-05-22 11:53:49'),
       (5, 'Volkswagen', 2000, 'economy', 4, 'Golf 2', '7845 IX-3', 1992, '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (6, 'BMW', 10000, 'premium', 9, 'X6', '7316 AA-3', 2010, '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (7, 'Toyota', 15000, 'premium', 12, 'RAV 4', '7894 AA-3', 2015, '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (8, 'Audi', 3000, 'standart', 7, '80 B4', '4848 EX-3', 1994, '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (9, 'Audi', 20000, 'premium', 15, 'Q7', '3333 II-3', 2021, '2022-05-22 11:54:19', '2022-05-22 11:54:19'),
       (10, 'VAZ', 100, 'economy', 2, '2101', '1111 II-1', 1950, '2022-05-22 11:54:20', '2022-05-22 11:54:20')
;

INSERT INTO `driver` (`id`, `last_name`, `name`, `middle_name`, `birth_date`, `licence`, `date_creation`,
                      `last_modified`)
VALUES (1, 'Ivanov', 'Igor', 'Petrovich', '1969-04-22', 'IN789126', '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (2, 'Grishkina', 'Antonina', 'Semenovna', '1987-05-19', 'OM136975', '2022-05-22 11:53:49',
        '2022-05-22 11:53:49'),
       (3, 'Lopuch', 'Akakiy', 'Grigoryevich', '1954-08-13', 'PP569412', '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (4, 'Mamontov', 'Petro', 'Petrovich', '1990-06-29', 'OB139564', '2022-05-22 11:53:49', '2022-05-22 11:53:49'),
       (5, 'Ponchikova', 'Konchita', 'Evlampievna', '1989-06-17', 'PL999666', '2022-05-22 11:53:49',
        '2022-05-22 11:53:49'),
       (6, 'Mamontova', 'Luiza', 'Viktorovna', '1985-05-29', 'AA457854', '2022-05-22 11:54:49', '2022-05-22 11:54:49'),
       (7, 'Platonov', 'Igor', 'Evlampievich', '1976-04-17', 'HH587945', '2022-05-22 11:54:52', '2022-05-22 11:54:52'),
       (8, 'Pyatkin', 'Olaf', 'Sebastyanovich', '1991-01-01', 'PG457894', '2022-05-22 11:55:01', '2022-05-22 11:55:01'),
       (9, 'Lozhkin', 'Denis', 'Leonidovich', '1985-10-15', 'II456789', '2022-05-22 11:55:30', '2022-05-22 11:55:30')
;

INSERT INTO `car_driver` (`car_id`, `driver_id`)
VALUES (1, 1),
       (1, 2),
       (3, 5),
       (9, 8),
       (4, 1),
       (4, 5),
       (10, 3);

SET foreign_key_checks = 1;