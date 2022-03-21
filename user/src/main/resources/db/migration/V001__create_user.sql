create table users(
    id bigint primary key generated always as identity,
    username text not null,
    password text not null,
    dollar_balance numeric,
    enabled boolean not null default true,
    created_on timestamp not null default current_timestamp,
    updated_on timestamp not null default current_timestamp
);

create table user_stock_balances(
    id_user bigint references users(id),
    id_stock bigint not null,
    stock_symbol text not null,
    stock_name text not null,
    volume bigint not null default 0,
    created_on timestamp not null default current_timestamp,
    updated_on timestamp not null default current_timestamp,
    primary key (id_user, id_stock)
);

create table user_orders(
    id bigint primary key generated always as identity,
    id_user bigint references users(id),
    id_stock bigint not null,
    stock_symbol text not null,
    stock_name text not null,
    volume bigint not null default 0,
    volume_remaining bigint not null default 0,
    price numeric not null default 0.0,
    type int not null,
    status int not null,
    created_on timestamp not null default current_timestamp,
    updated_on timestamp not null default current_timestamp
);

INSERT INTO public.users
(username, "password", dollar_balance, enabled, created_on, updated_on)
VALUES('Leonardo', '12345', 1000, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.users
(username, "password", dollar_balance, enabled, created_on, updated_on)
VALUES('Lucas', '12345', 1000, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.users
(username, "password", dollar_balance, enabled, created_on, updated_on)
VALUES('Gabriel', '12345', 1000, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.users
(username, "password", dollar_balance, enabled, created_on, updated_on)
VALUES('Juliano', '12345', 1000, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);