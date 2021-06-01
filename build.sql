create database wabby;
use wabby;
create table Tips
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    date DATETIME NOT NULL,
    type VARCHAR(6) NOT NULL,
    starNum INT NOT NULL DEFAULT 0,
    content TEXT NOT NULL
);

create table Comment
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    starNum INT NOT NULL DEFAULT 0,
    date DATETIME NOT NULL ,
    floor INT NOT NULL DEFAULT 1,
    Tips_id INT NOT NULL,
    FOREIGN KEY (Tips_id) REFERENCES Tips(id)
)