use book_store;

SET FOREIGN_KEY_CHECKS = 0;

LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/author.csv' IGNORE 
INTO TABLE author
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/book.csv' IGNORE 
INTO TABLE book
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/cart.csv' IGNORE 
INTO TABLE cart
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';


LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/log.csv' IGNORE 
INTO TABLE log
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';


LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/orders.csv' IGNORE 
INTO TABLE orders
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/publisher.csv' IGNORE 
INTO TABLE publisher
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/quantity_table.csv' IGNORE 
INTO TABLE quantity_table
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/Arsanuos/eclipse-workspace/BookStore/data_files/user.csv' IGNORE 
INTO TABLE user
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';


SET FOREIGN_KEY_CHECKS = 1;