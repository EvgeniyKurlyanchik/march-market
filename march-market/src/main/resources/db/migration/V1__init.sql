create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);

insert into products (title, price)
values ('Bread', 32),
       ('Milk', 120),
       ('Butter', 320),
       ('Cheese', 500);


create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null
);

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('bob', '$2a$12$8LOWcb.PuUAcn3qFxstE9uVl89jCi2OEkC0IqAH8D51FBXHfZtB9i'),
        ('admin', '$2a$12$9B39MlkUqa0SucqFGlMyJOD3jUTeRaHHEDncoKQBVy7nT8UrGXpT2');
insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);