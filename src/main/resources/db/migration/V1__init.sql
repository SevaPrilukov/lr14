CREATE TABLE spisokticket
(
    id    serial primary key,
    passenger varchar(100),
    route varchar(250),
    price INTEGER
);

INSERT INTO spisokticket (passenger, route, price)
VALUES
('Прилуков В.А', 'Ледокол', 1000),
('Петров И.Д', 'Река',2000),
('Иванов И.И', 'Мель', 3000);

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  boolean      NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users
VALUES ('admin', '{noop}123', true),
       ('user', '{noop}456', true);

CREATE TABLE authorities
(
    username  varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (username)
            REFERENCES users (username)
);

INSERT INTO authorities
VALUES ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_USER'),
       ('user', 'ROLE_USER');