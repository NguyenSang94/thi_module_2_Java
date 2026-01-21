create table admin
(
    id       serial primary key,
    username varchar(255) not null unique,
    password varchar(255) not null
);

create table product
(
    id    serial primary key,
    name  varchar(100)   not null,
    brand varchar(50)    not null,
    price decimal(12, 2) not null,
    stock int            not null

);

create table customer
(
    id      serial primary key,
    name    varchar(100)        not null,
    phone   varchar(20)         not null,
    email   varchar(100) unique not null,
    address varchar(255)        not null

);

/* Tạo Function getAllCustomer*/
create or replace function get_all_customer()
    returns table
            (
                id      int,
                name    varchar(100),
                phone   varchar(20),
                email   varchar(100),
                address varchar(255)
            )
as
$$
begin
    return query
        select c.id, c.name, c.phone, c.email, c.address from customer c;
end;
$$ language plpgsql;
select *
from customer;

/* Tạo Function getAllProduct*/
create or replace function get_all_product()
    returns table
            (
                id    int,
                name  VARCHAR,
                brand VARCHAR,
                price DECIMAL,
                stock INT
            )

as
$$
begin
    return query
        select p.id, p.name, p.brand, p.price, p.stock from product p;
end;
$$ language plpgsql;

/* Tạo Procedure add_Product*/
create or replace procedure add_product(
    p_name varchar(100),
    p_brand varchar,
    p_price numeric,
    p_stock integer
)
    language plpgsql
as
$$
begin
    insert into product(name, brand, price, stock) values (p_name, p_brand, p_price, p_stock);
end;
$$;
/* Tạo Procedure add_Customer*/
create or replace procedure add_customer(
    c_name varchar(100),
    c_phone varchar,
    c_email varchar,
    c_address varchar
)
    language plpgsql
as
$$
begin
    insert into customer(name, phone, email, address) values (c_name, c_phone, c_email, c_address);
end;
$$;
/* Tạo Function get_customer_by_id*/
create or replace function find_customer_by_id(id_in int)
    returns table
            (
                id      int,
                name    varchar,
                phone   varchar,
                email   varchar,
                address varchar
            )
as
$$
begin
    return query
        select cu.id, cu.name, cu.phone, cu.email, cu.address from customer cu where cu.id = id_in;
end;
$$ language plpgsql;
drop function find_customer_by_id;
/* Tạo Procedure update_Customer*/
create or replace procedure update_customer(
    id_in int,
    name_c varchar,
    phone_c varchar,
    email_c varchar,
    address_c varchar
)
    language plpgsql
as
$$
begin
    update customer
    set name    = name_c,
        phone   = phone_c,
        email   = email_c,
        address = address_c
    where id = id_in;

end;
$$;
drop function find_product_by_id;
/* Tạo Function get_product_by_id*/
create or replace function find_product_by_id(idp_in int)
    returns table
            (
                id    int,
                name  varchar,
                brand varchar,
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select pr.id,
               pr.name,
               pr.brand,
               pr.price,
               pr.stock
        from product pr
        where pr.id = idp_in;

end;
$$ language plpgsql;
/* Tạo Procedure update_Product*/
create or replace procedure update_product(
    id_in int,
    name_in varchar,
    brand_in varchar,
    price_in numeric,
    stock_in int
)
    language plpgsql
as
$$
begin
    update product
    set name  = name_in,
        brand = brand_in,
        price = price_in,
        stock = stock_in
    where id = id_in;
end;
$$;
/* Tạo Procedure delete_Customer*/
create or replace procedure delete_customer(id_in int)
    language plpgsql
as
$$
begin
    delete from customer where id = id_in;
end;
$$;
/* Tạo Procedure delete_Product*/
create or replace procedure delete_product(id_in int)
    language plpgsql
as
$$
begin
    delete from product where id = id_in;
end;
$$;
/*Tạo Function search_product_by_brand*/
create or replace function search_product_by_brand(brand_key varchar)
    returns table
            (
                id    int,
                name  varchar,
                brand varchar,
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select p.id, p.name, p.brand, p.price, p.stock
        from product p
        where p.brand ilike concat('%', brand_key, '%');
end;
$$ language plpgsql;
/*Tạo Function search_product_by_price_range*/
create or replace function search_product_by_price_range(min_price numeric, max_price numeric)
    returns table
            (
                id    int,
                name  varchar,
                brand varchar,
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select p.id, p.name, p.brand, p.price, p.stock
        from product p
        where p.price between min_price and max_price;
end;
$$ language plpgsql;
/*Tạo Function search_product_by_name_and_stock*/
create or replace function search_product_by_name_and_stock(keyword varchar)
    returns table
            (
                id    int,
                name  varchar,
                brand varchar,
                price numeric,
                stock int
            )
as
$$
begin
    return query
        select p.id, p.name, p.brand, p.price, p.stock
        from product p
        where p.name ilike concat('%', keyword , '%') and p.stock > 0;
end;
$$ language plpgsql;
