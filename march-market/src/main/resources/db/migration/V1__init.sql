create table if not exists products
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
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
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
values ('bob', '$2a$12$/HKYvMY7cWYApSnjPheKc.0VsVyIUBy4YEcVLz0Tm5Vm9eNeRTw2m'),
        ('admin', '$2a$12$9B39MlkUqa0SucqFGlMyJOD3jUTeRaHHEDncoKQBVy7nT8UrGXpT2');
insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    address     varchar,
    phone       varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    quantity          int    not null,
    order_id          bigint not null references orders (id),
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp,
    updated_at        timestamp
);

insert into orders (user_id, total_price, address, phone)
values (1, 100, 'address', '88007004800');