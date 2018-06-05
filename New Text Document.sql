-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema book_store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema book_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `book_store` DEFAULT CHARACTER SET utf8 ;
USE `book_store` ;

-- -----------------------------------------------------
-- Table `book_store`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`publisher` (
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(120) NOT NULL,
  `telephone` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book_store`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`book` (
  `ISBN` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `pyear` YEAR(4) NOT NULL,
  `category` VARCHAR(10) NOT NULL,
  `publisher_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ISBN`, `publisher_name`),
  INDEX `category_index` (`category` ASC),
  INDEX `fk_book_publisher1_idx` (`publisher_name` ASC),
  CONSTRAINT `fk_book_publisher1`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `book_store`.`publisher` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book_store`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`author` (
  `author_name` VARCHAR(50) NOT NULL,
  `ISBN` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`author_name`, `ISBN`),
  INDEX `author_name_idx` (`ISBN` ASC),
  CONSTRAINT `author_name`
    FOREIGN KEY (`ISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book_store`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`user` (
  `user name` VARCHAR(40) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `shipping_address` VARCHAR(45) NOT NULL,
  `privilege` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE INDEX `user name_UNIQUE` (`user name` ASC),
  UNIQUE INDEX `phoneNumber_UNIQUE` (`phone_number` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book_store`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`cart` (
  `email` VARCHAR(45) NOT NULL,
  `ISBN` VARCHAR(50) NOT NULL,
  `quantity` INT(11) NOT NULL,
  PRIMARY KEY (`email`, `ISBN`),
  INDEX `cart-bookfk_idx` (`ISBN` ASC),
  CONSTRAINT `cart-bookfk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cart-emailfk`
    FOREIGN KEY (`email`)
    REFERENCES `book_store`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book_store`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`log` (
  `email` VARCHAR(45) NOT NULL,
  `ISBN` VARCHAR(50) NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `sell_date` DATETIME NOT NULL,
  PRIMARY KEY (`email`, `ISBN`, `sell_date`),
  INDEX `log_ISBNfk_idx` (`ISBN` ASC),
  CONSTRAINT `log_ISBNfk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `log_emailfk`
    FOREIGN KEY (`email`)
    REFERENCES `book_store`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book_store`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`orders` (
  `amount` INT(11) NOT NULL,
  `ISBN` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `book_order`
    FOREIGN KEY (`ISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book_store`.`quantity_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`quantity_table` (
  `ISBN` VARCHAR(50) NOT NULL,
  `threshold` INT(11) NULL DEFAULT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `book_quantity`
    FOREIGN KEY (`ISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `book_store`;

DELIMITER $$
USE `book_store`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `book_store`.`orders_BEFORE_DELETE`
BEFORE DELETE ON `book_store`.`orders`
FOR EACH ROW
BEGIN
	UPDATE quantity_table SET quantity = OLD.amount + quantity WHERE OLD.ISBN = quantity_table.ISBN;
END$$

USE `book_store`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `book_store`.`quantity_table_AFTER_UPDATE`
AFTER UPDATE ON `book_store`.`quantity_table`
FOR EACH ROW
BEGIN
	IF NEW.quantity < NEW.threshold THEN
    -- assuming that the order from the publisher is the value that makes the current quantity above the threshold by 50%
		SET @x = (SELECT COUNT(*) 
				  FROM orders
				  WHERE orders.ISBN = NEW.ISBN);
		IF @x = 0 THEN 
			INSERT INTO orders 
            VALUES (NEW.ISBN, OLD.threshold - NEW.quantity + CEILING(1/2 * OLD.threshold));
        END IF;
    END IF;
END$$

USE `book_store`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `book_store`.`quantity_table_BEFORE_UPDATE`
BEFORE UPDATE ON `book_store`.`quantity_table`
FOR EACH ROW
BEGIN
	IF NEW.quantity < 0 THEN
		-- to prevent sql from execute the operation itself call a not valid statement.
		INSERT INTO non_existing_table VALUES("dummy", "dummy");
	END IF;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
