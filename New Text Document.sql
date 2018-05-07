-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema BOOK_STORE
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BOOK_STORE
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BOOK_STORE` DEFAULT CHARACTER SET utf8 ;
USE `BOOK_STORE` ;

-- -----------------------------------------------------
-- Table `BOOK_STORE`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOK_STORE`.`publisher` (
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(120) NOT NULL,
  `telephone` VARCHAR(15) NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOK_STORE`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOK_STORE`.`book` (
  `ISBN` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `pyear` YEAR NOT NULL,
  `category` VARCHAR(10) NOT NULL,
  `publisher_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ISBN`, `publisher_name`),
  INDEX `category_index` (`category` ASC),
  INDEX `fk_book_publisher1_idx` (`publisher_name` ASC),
  CONSTRAINT `fk_book_publisher1`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `BOOK_STORE`.`publisher` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOK_STORE`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOK_STORE`.`author` (
  `author_name` VARCHAR(50) NOT NULL,
  `ISBN` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`author_name`, `ISBN`),
  INDEX `author_name_idx` (`ISBN` ASC),
  CONSTRAINT `author_name`
    FOREIGN KEY (`ISBN`)
    REFERENCES `BOOK_STORE`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOK_STORE`.`quantity_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOK_STORE`.`quantity_table` (
  `ISBN` VARCHAR(50) NOT NULL,
  `threshold` INT NULL,
  `quantity` INT NULL,
  `price` FLOAT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `book_quantity`
    FOREIGN KEY (`ISBN`)
    REFERENCES `BOOK_STORE`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOK_STORE`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOK_STORE`.`orders` (
  `amount` INT NOT NULL,
  `ISBN` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `book_order`
    FOREIGN KEY (`ISBN`)
    REFERENCES `BOOK_STORE`.`book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DELIMITER $$
USE `BOOK_STORE`$$
CREATE DEFINER = CURRENT_USER TRIGGER `BOOK_STORE`.`quantity_table_BEFORE_UPDATE` BEFORE UPDATE ON `quantity_table` FOR EACH ROW
BEGIN
	IF NEW.quantity < 0 THEN
		-- to prevent sql from execute the operation itself call a not valid statement.
		INSERT INTO non_existing_table VALUES("dummy", "dummy");
	END IF;
END$$

CREATE DEFINER = CURRENT_USER TRIGGER `BOOK_STORE`.`quantity_table_AFTER_UPDATE` AFTER UPDATE ON `quantity_table` FOR EACH ROW
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

CREATE DEFINER = CURRENT_USER TRIGGER `BOOK_STORE`.`orders_BEFORE_DELETE` BEFORE DELETE ON `orders` FOR EACH ROW
BEGIN
	UPDATE quantity_table SET quantity = OLD.amount + quantity WHERE OLD.ISBN = quantity_table.ISBN;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
