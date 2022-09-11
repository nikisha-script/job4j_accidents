CREATE TABLE if not exists types (
    id serial PRIMARY key,
    name text
);

create table if not exists rules (
    id serial primary key,
    name text
);

create table if not exists accident (
    id serial primary key,
    name text,
    address text,
    type_id int REFERENCES types(id)
);

create table if not exists accident_rules (
    accident_id int REFERENCES accident(id),
    rule_id int REFERENCES rules(id)
);

insert into rules(name) values('Статья. 1'),('Статья. 2'),('Статья. 3');
insert into types(name) values('Две машины'),('Машина и человек'),('Машина и велосипед');
