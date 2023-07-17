-- MySQL Script generated by MySQL Workbench
-- пт, 14-лип-2023 23:48:37 +0300
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gift_card
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gift_card
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gift_card` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `gift_card` ;

-- -----------------------------------------------------
-- Table `gift_card`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_card`.`customer` ;

CREATE TABLE IF NOT EXISTS `gift_card`.`customer` (
                                                      `id` INT NOT NULL AUTO_INCREMENT,
                                                      `name` VARCHAR(45) NOT NULL,
                                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gift_card`.`gift_certificate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_card`.`gift_certificate` ;

CREATE TABLE IF NOT EXISTS `gift_card`.`gift_certificate` (
                                                              `id` INT NOT NULL AUTO_INCREMENT,
                                                              `name` VARCHAR(45) NULL DEFAULT NULL,
                                                              `description` VARCHAR(45) NULL DEFAULT NULL,
                                                              `price` DOUBLE NULL DEFAULT NULL,
                                                              `create_date` DATETIME NULL DEFAULT NULL,
                                                              `last_update_date` DATETIME NULL DEFAULT NULL,
                                                              `duration` INT NULL DEFAULT NULL,
                                                              PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 40
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gift_card`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_card`.`tag` ;

CREATE TABLE IF NOT EXISTS `gift_card`.`tag` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(45) NULL DEFAULT NULL,
                                                 PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 24
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gift_card`.`gift_certificate_has_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_card`.`gift_certificate_has_tag` ;

CREATE TABLE IF NOT EXISTS `gift_card`.`gift_certificate_has_tag` (
                                                                      `gift_certificate_id` INT NOT NULL,
                                                                      `tag_id` INT NOT NULL,
                                                                      PRIMARY KEY (`gift_certificate_id`, `tag_id`),
                                                                      INDEX `fk_new_table_has_tag_tag1_idx` (`tag_id` ASC) VISIBLE,
                                                                      INDEX `fk_new_table_has_tag_new_table_idx` (`gift_certificate_id` ASC) VISIBLE,
                                                                      CONSTRAINT `fk_new_table_has_tag_new_table`
                                                                          FOREIGN KEY (`gift_certificate_id`)
                                                                              REFERENCES `gift_card`.`gift_certificate` (`id`),
                                                                      CONSTRAINT `fk_new_table_has_tag_tag1`
                                                                          FOREIGN KEY (`tag_id`)
                                                                              REFERENCES `gift_card`.`tag` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gift_card`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_card`.`order` ;

CREATE TABLE IF NOT EXISTS `gift_card`.`order` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `customer_id` INT NOT NULL,
                                                   `creation_time` DATETIME NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   INDEX `fk_order_customer1_idx` (`customer_id` ASC) VISIBLE,
                                                   CONSTRAINT `fk_order_customer1`
                                                       FOREIGN KEY (`customer_id`)
                                                           REFERENCES `gift_card`.`customer` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gift_card`.`order_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_card`.`order_item` ;

CREATE TABLE IF NOT EXISTS `gift_card`.`order_item` (
                                                        `id` INT NOT NULL AUTO_INCREMENT,
                                                        `quantity` INT NOT NULL,
                                                        `order_id` INT NOT NULL,
                                                        `gift_certificate_id` INT NOT NULL,
                                                        PRIMARY KEY (`id`),
                                                        INDEX `fk_order_item_order1_idx` (`order_id` ASC) VISIBLE,
                                                        INDEX `fk_order_item_gift_certificate1_idx` (`gift_certificate_id` ASC) VISIBLE,
                                                        CONSTRAINT `fk_order_item_gift_certificate1`
                                                            FOREIGN KEY (`gift_certificate_id`)
                                                                REFERENCES `gift_card`.`gift_certificate` (`id`),
                                                        CONSTRAINT `fk_order_item_order1`
                                                            FOREIGN KEY (`order_id`)
                                                                REFERENCES `gift_card`.`order` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;