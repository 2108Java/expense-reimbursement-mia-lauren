create table users(
-- user_id serial unique,
username varchar(20) unique not null,
password varchar(20) not null,
-- first_name varchar(20) not null,
-- last_name varchar(20) not null,
user_type varchar(15) not null
);

create table tickets(
ticket_id serial,
username varchar(20) references users(username) not null,
expense_type varchar(7) not null,
amount numeric not null,
description varchar(100),
submitted_on date not null,
status varchar(8) not null
);