CREATE DATABASE IF NOT EXISTS petstagram;

USE petstagram;

CREATE TABLE IF NOT EXISTS users(
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL ,
    password varchar(255),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    city VARCHAR(80),
    telephone VARCHAR(20),
    INDEX(last_name)
);


CREATE TABLE IF NOT EXISTS pets(
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    type VARCHAR(30),
    age INTEGER DEFAULT 0,
    sex VARCHAR(30) default 'male',
    user_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS posts(
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    postTime VARCHAR(30),
    likes INTEGER unsigned default 0,
    user_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
)