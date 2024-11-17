CREATE TABLE if NOT EXISTS beer
(
    id             integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    beer_name      varchar(255),
    price          decimal,
    created_date   timestamp,
    modified_date timestamp
);