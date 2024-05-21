CREATE
DATABASE inventory;

USE
inventory;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `username`     varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `role`     varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users (username, password, role)
VALUES ('admin', 'admin', 'admin'),
       ('wan', 'wan', 'user');

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `price` double NOT NULL,
    `quantity`    int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO products (name, description, price, quantity)
VALUES ('Apple', 'Apple', 1.0, 100),
       ('Banana', 'Banana', 2.0, 100),
       ('Orange', 'Orange', 3.0, 100),
       ('Grape', 'Grape', 4.0, 100),
       ('Strawberry', 'Strawberry', 5.0, 100);

DROP TABLE IF EXISTS `user_activity`;

CREATE TABLE user_activity
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    user_id   INT,
    activity  VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO user_activity (user_id, activity)
VALUES (1, 'Login'),
       (1, 'Logout'),
       (2, 'Login'),
       (2, 'Logout');


