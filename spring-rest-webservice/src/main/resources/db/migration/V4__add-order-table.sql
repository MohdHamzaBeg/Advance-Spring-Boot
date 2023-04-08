drop table if exists beer_order;

CREATE TABLE `beer_order`
(
    id                 varchar(36) NOT NULL,
    created_date       datetime(6)  DEFAULT NULL,
    customer_ref       varchar(255) DEFAULT NULL,
    last_modified_date datetime(6)  DEFAULT NULL,
    version            bigint       DEFAULT NULL,
    customer_id        int(36)  DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE = InnoDB;