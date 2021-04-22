CREATE TABLE orders(
 id VARCHAR(100) PRIMARY KEY ,
 order_time DATETIME NOT NULL,
 total_count INT NOT NULL,
 total_amount DOUBLE(11,2) NOT NULL ,
 state INT NOT NULL,
 user_id INT NOT NULL,
 FOREIGN KEY(user_id) REFERENCES `user`(id)
)