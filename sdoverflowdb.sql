-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sdoverflowdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sdoverflowdb` ;

-- -----------------------------------------------------
-- Schema sdoverflowdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sdoverflowdb` DEFAULT CHARACTER SET utf8 ;
USE `sdoverflowdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) NOT NULL,
  `street2` VARCHAR(100) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  `country` VARCHAR(2) NULL,
  `zip` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employer` ;

CREATE TABLE IF NOT EXISTS `employer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `hiring` VARCHAR(45) NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `address_id_idx` (`address_id` ASC),
  CONSTRAINT `address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile` ;

CREATE TABLE IF NOT EXISTS `profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `employed` TINYINT NULL,
  `cohort` VARCHAR(45) NULL,
  `address_id` INT NULL,
  `employer_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `address_id_idx` (`address_id` ASC),
  INDEX `employer_id_idx` (`employer_id` ASC),
  CONSTRAINT `fk_profile_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_employer`
    FOREIGN KEY (`employer_id`)
    REFERENCES `employer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(20) NULL,
  `enabled` TINYINT NULL,
  `profile_id` INT NOT NULL,
  `admin` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `profile_id_idx` (`profile_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `profile_id`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `created_at` TIMESTAMP NULL,
  `updated_at` TIMESTAMP NULL,
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `category_id_idx` (`category_id` ASC),
  CONSTRAINT `fk_post_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `description` VARCHAR(1000) NULL,
  `created_at` TIMESTAMP NULL,
  `updated_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `post_id_idx` (`post_id` ASC),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_post`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `technology`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `technology` ;

CREATE TABLE IF NOT EXISTS `technology` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(500) NULL,
  `payscale` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employer_technology`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `employer_technology` ;

CREATE TABLE IF NOT EXISTS `employer_technology` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `technology_id` INT NOT NULL,
  `employer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `employer_id_idx` (`employer_id` ASC),
  INDEX `technology_id_idx` (`technology_id` ASC),
  CONSTRAINT `employer_id`
    FOREIGN KEY (`employer_id`)
    REFERENCES `employer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `technology_id`
    FOREIGN KEY (`technology_id`)
    REFERENCES `technology` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `profile_technology`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `profile_technology` ;

CREATE TABLE IF NOT EXISTS `profile_technology` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `technology_id` INT NOT NULL,
  `profile_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `technology_id_idx` (`technology_id` ASC),
  INDEX `profile_id_idx` (`profile_id` ASC),
  CONSTRAINT `fk_profile_technology`
    FOREIGN KEY (`technology_id`)
    REFERENCES `technology` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_profile_id`
    FOREIGN KEY (`profile_id`)
    REFERENCES `profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment_vote` ;

CREATE TABLE IF NOT EXISTS `comment_vote` (
  `user_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  `vote` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `comment_id`),
  INDEX `comment_id_idx` (`comment_id` ASC),
  CONSTRAINT `fk_commentvote_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentvote_comment`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO sdoverflowuser@localhost;
 DROP USER sdoverflowuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'sdoverflowuser'@'localhost' IDENTIFIED BY 'skills';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'sdoverflowuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `country`, `zip`) VALUES (1, '123 Hello World Lane', NULL, 'Denver', 'CO', 'US', '80134');

COMMIT;


-- -----------------------------------------------------
-- Data for table `employer`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `employer` (`id`, `name`, `hiring`, `address_id`) VALUES (1, 'Taco Bell', 'No', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `email`, `employed`, `cohort`, `address_id`, `employer_id`) VALUES (1, 'Jackson', 'Brown', 'Jackson@Jackson.com', 0, 'SD14', 1, 1);
INSERT INTO `profile` (`id`, `first_name`, `last_name`, `email`, `employed`, `cohort`, `address_id`, `employer_id`) VALUES (2, 'admin', 'admin', 'admin@overflow.com', 1, NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `user` (`id`, `username`, `password`, `role`, `enabled`, `profile_id`, `admin`) VALUES (1, 'jackson', '$2a$10$p9yj2W/H0WfK65ZL8u7bJuUODhIHBvGZqaGInL4ZnhjA/bEwnXayq', 'standard', 1, 1, 0);
INSERT INTO `user` (`id`, `username`, `password`, `role`, `enabled`, `profile_id`, `admin`) VALUES (2, 'admin', 'admin', 'admin', 1, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `category` (`id`, `name`, `description`) VALUES (1, 'Java', 'Java is a compiled language');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `post` (`id`, `name`, `description`, `created_at`, `updated_at`, `user_id`, `category_id`) VALUES (1, 'Java question', 'This is the original post', '2018-06-01 10:36:48', '2018-06-01 10:54:50', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `comment` (`id`, `post_id`, `user_id`, `description`, `created_at`, `updated_at`) VALUES (1, 1, 1, 'Java is awesome', '2018-06-01 10:29:39', '2018-06-04 10:36:48');

COMMIT;


-- -----------------------------------------------------
-- Data for table `technology`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `technology` (`id`, `name`, `description`, `payscale`) VALUES (1, 'Java', 'Java was created by Sun Microsystems', '80,000');

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment_vote`
-- -----------------------------------------------------
START TRANSACTION;
USE `sdoverflowdb`;
INSERT INTO `comment_vote` (`user_id`, `comment_id`, `vote`) VALUES (1, 1, NULL);

COMMIT;

