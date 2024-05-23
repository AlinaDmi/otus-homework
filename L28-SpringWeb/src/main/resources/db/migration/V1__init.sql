create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

create table address
(
    street varchar(50),
    client_id bigserial not null primary key references client (id)
);

create table box
(
    id   bigserial not null primary key,
    name varchar(50)
);

