DROP TABLE IF EXISTS users;

CREATE TABLE users (
id int AUTO_INCREMENT primary key,
username varchar(255),
password varchar(255),
created_at date,
created_by varchar(32)
);


INSERT INTO users (username,password, created_at, created_by) VALUES ("Yves","yyy", now(), "CONSOLE");
INSERT INTO users (username,password, created_at, created_by) VALUES ("Bertil","bbb", now(), "CONSOLE");
INSERT INTO users (username,password, created_at, created_by) VALUES ("Gertrude","ggg", now(), "CONSOLE");
INSERT INTO users (username,password, created_at, created_by) VALUES ("Todd","ttt", now(), "CONSOLE");
