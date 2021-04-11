# Spring Boot Security JWT with User and Roles

This is a small example that shows how to use JWT with Spring Boot
Security.

## Getting started


## Database
This project uses Postgresql. You can switch to a different one by
importing a different maven dependencies i pom.xml.

Change the application.properties to use a different database URI

## Below is the DDL to insert some data into the database

### User table
`create table users
(
user_id  serial               not null
constraint users_pk
primary key,
email    varchar(55)          not null,
username varchar(100)         not null,
password varchar(64)          not null,
enabled  boolean default true not null
);

alter table users
owner to postgres;

create unique index users_email_uindex
on users (email);

INSERT INTO public.users (user_id, email, username, password, enabled) VALUES (1, 'patrick@demo.com', 'patrick', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', true);
INSERT INTO public.users (user_id, email, username, password, enabled) VALUES (2, 'alex@demo.com', 'alex', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', true);
INSERT INTO public.users (user_id, email, username, password, enabled) VALUES (3, 'john@demo.com', 'john', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', true);
INSERT INTO public.users (user_id, email, username, password, enabled) VALUES (4, 'namhm@demo.com', 'namhm', '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', true);
INSERT INTO public.users (user_id, email, username, password, enabled) VALUES (5, 'admin@demo.com', 'admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', true);`

### Role table

`create table roles
(
role_id serial      not null
constraint roles_pk
primary key,
name    varchar(45) not null
);

alter table roles
owner to postgres;

INSERT INTO public.roles (role_id, name) VALUES (1, 'USER');
INSERT INTO public.roles (role_id, name) VALUES (2, 'CREATOR');
INSERT INTO public.roles (role_id, name) VALUES (3, 'EDITOR');
INSERT INTO public.roles (role_id, name) VALUES (4, 'ADMIN');`

### Users_Roles table

`create table users_roles
(
user_id integer not null
constraint user_fk_idx
unique
constraint user__fk
references users,
role_id integer not null
constraint role_fk_idx
unique
constraint role__fk
references roles
);

alter table users_roles
owner to postgres;

INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (4, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (5, 4);`

### Product Table

`create table product
(
id     serial           not null,
name   varchar(45)      not null,
brand  varchar(45)      not null,
madein varchar(45)      not null,
price  double precision not null
);

alter table product
owner to postgres;

INSERT INTO public.product (id, name, brand, madein, price) VALUES (1, 'TV', 'Sony', 'Japan', 2000);
INSERT INTO public.product (id, name, brand, madein, price) VALUES (3, 'PC', 'Lenovo', 'Kina', 5000);
INSERT INTO public.product (id, name, brand, madein, price) VALUES (4, 'PC', 'Lenovo', 'Kina', 4990);`
