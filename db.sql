DROP TABLE if exists users;
CREATE TABLE users (
  id serial PRIMARY KEY not null,
  username VARCHAR(30) UNIQUE ,
  password VARCHAR(150),
  admin boolean default false,
  name VARCHAR(150) not null,
  paternalName VARCHAR(150) not null,
  maternalName VARCHAR(150) not null,
  email VARCHAR(150) not null
);

INSERT INTO users(username, password,admin,name,paternalName,maternalName,email) VALUES ('ceo','123',true,'Mark Anthony','Arreguin','Gonzalez','ceo@opal.com');
INSERT INTO users(username, password,name,paternalName,maternalName,email) VALUES ('dto','123','Richy','Gallegos','Gallegos','dto@opal.com');
INSERT INTO users(username, password,name,paternalName,maternalName,email) VALUES ('mochilas','123','Cristian','Perez',':v','mochilas@opal.com');

-- Get: Employee Routes
select id,idRoute from users join routes on users.id = routes.idEmployee;

-- Get employee routes
select idRoute, latlong from users u
  join routes on u.id = routes.idEmployee
  join customers c on routes.idCustomer = c.id
where idEmployee = 1;


drop table if exists routes;
create table routes(
  idEmployee int not null references users(id),
  idRoute int not null,
  idCustomer int not null references customers(id),
  primary key(idEmployee,idCustomer,idRoute)
);

insert into routes(idEmployee, idRoute, idCustomer) VALUES (1,1,1);
insert into routes(idEmployee, idRoute, idCustomer) VALUES (1,1,2);
insert into routes(idEmployee, idRoute, idCustomer) VALUES (1,2,3);

drop table if exists customers;
create table customers (
  id serial primary key not null,
  name varchar(150) not null,
  phone varchar(150) not null,
  email varchar(150) not null,
  latlong point
);

insert into customers(name, phone, email,latlong) values ('Cris', 'pj', 'creshandkesh@gmail.com', point(20.541397, -100.816643));
insert into customers(name, phone, email,latlong) values ('Carmona con kk', '123123', 'carmonakk@gmail.com', point(20.540882, -100.815511));
insert into customers(name, phone, email,latlong) values ('pakorizo', '123123', 'pako@gmail.com', point(20.524687, -100.796123));


drop table if exists products;
create table products(
  id serial primary key not null,
  name varchar(150) not null ,
  description varchar(150) not null,
  image varchar(100),
  stock int not null,
  available int not null
);

update products set image = null;
alter table products alter column image type varchar(100);

insert into products(name, description, stock) values ('Dona de chocolate','La dona llena de negro chocolate',10);
insert into products(name, description, stock) values ('Dona de fresa','Dona llena de fresa',10);
insert into products(name, description, stock) values ('Dona de coco','Dona con glaseado y pedazos de coco',10);
insert into products(name, description, stock) values ('Dona de nuez','Dona con glaseado de nuez',10);
insert into products(name, description, stock) values ('Dona glaseada','Una dona bien glaseada',10);
insert into products(name, description, stock) values ('Dona de crema','Dona con mucha crema',10);
insert into products(name, description, stock) values ('Donita','mini donita',10);
insert into products(name, description, stock) values ('Donota','Una donota para los que tienen hambre',10);

drop table if exists productPrices;
create table productPrices(
  productId int references products(id),
  date timestamp,
  price numeric
);

create index pricesid on productPrices (productId) ;

insert into productPrices(productId, date, price) values (1,now(),4);
insert into productPrices(productId, date, price) values (2,now(),4);
insert into productPrices(productId, date, price) values (3,now(),5);
insert into productPrices(productId, date, price) values (4,now(),5);
insert into productPrices(productId, date, price) values (5,now(),6);
insert into productPrices(productId, date, price) values (6,now(),10);
insert into productPrices(productId, date, price) values (7,now(),2);
insert into productPrices(productId, date, price) values (8,now(),12);
insert into productPrices(productId, date, price) values (8,now(),12.5);

-- Obtener el producto
select id, name, description, price from products join productPrices on id=productId
where date = (SELECT
                  MAX(date)
                from productPrices
                where id = productId and date <= now()) order by id;


drop table if exists orders;
create table orders(
  id serial primary key not null,
  customerId int references customers(id),
  status boolean default false,
  orderDate date,
  employeeId int references users(id)
);

select id, name, description from products  where stock > 1;

insert into orders(customerId, orderDate,employeeId) VALUES (1,now(),1);
insert into customer_order(orderId, productId, quantity) VALUES (1,1,3);
insert into customer_order(orderId, productId, quantity) VALUES (1,2,3);
insert into customer_order(orderId, productId, quantity) VALUES (1,3,3);
insert into customer_order(orderId, productId, quantity) VALUES (1,4,3);


select orderid, customerId, status, orderdate, customer_order.productId, quantity, p.name, p.description, price, price*quantity as total
from orders o
  join customer_order on o.id = customer_order.orderId
  join products p on customer_order.productId = p.id
  join productPrices on p.id=productPrices.productId
where date = (SELECT
                MAX(date)
              from productPrices
              where p.id = productPrices.productId and date <= now());

select orderid o_orderid, customerId o_customerid, status o_status, orderdate o_orderdate, p.id p_id, p.name p_name, p.description p_description, price p_price from orders o
                   join customer_order on o.id = customer_order.orderId
                   join products p on customer_order.productId = p.id
                   join productPrices on p.id=productPrices.productId
                   where date = (SELECT
                   MAX(date)
                   from productPrices
                   where p.id = productPrices.productId and date <= now()) and orderid = 1;

drop table if exists customer_order;
create table customer_order(
  orderId int references orders(id),
  productId int references products(id),
  quantity int not null,
  primary key (orderId,productId)
);

drop table if exists tokens;
create table tokens(
  id serial primary key not null,
  userid int references users(id),
  token varchar(100) not null
);

ALTER TABLE productprices ALTER COLUMN date TYPE timestamp USING date::timestamp;


select orderid o_orderid, customerId o_customerid, status o_status, orderdate o_orderdate,quantity o_quantity, p.id p_id, p.name p_name, p.description p_description, price p_price
    from orders o
                     join customer_order on o.id = customer_order.orderId
                     left join products p on customer_order.productId = p.id
                     join productPrices on p.id=productPrices.productId
                     where date = (SELECT MAX(date)
                        from productPrices
                         where p.id = productPrices.productId and date <= now()) and orderid = 1;


select productId, name, stock, quantity from products right outer join customer_order o on products.id = o.productId where orderId = 1;

select id, name, price, stock from products join productPrices on id=productId
where date = (SELECT
                MAX(date)
              from productPrices
              where id = productId and date <= now()) and id not in ((select productId
            from customer_order where orderId = 1)) order by id;

select productId, name, stock, quantity from products right outer join customer_order o on products.id = o.productId where orderId = 1;


select * from customers where id not in (select idCustomer from routes join customers c on routes.idCustomer = c.id order by IdRoute);

select users.id as id, token, username, name from tokens join users on tokens.userid = users.id;

create table firetoken(
  id serial not null primary key ,
  token text
);

