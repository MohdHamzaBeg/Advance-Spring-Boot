drop table if exists beer;
drop table if exists customer;
create table beer (id integer not null auto_increment, beer_name varchar(50) not null, mfg datetime(6), price decimal(38,2) not null, primary key (id)) engine=InnoDB;
create table customer (id integer not null auto_increment, created_date datetime(6), customer_name varchar(255), last_modified_date datetime(6), version float(23) not null, primary key (id)) engine=InnoDB;
