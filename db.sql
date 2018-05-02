DROP TABLE if exists users;
CREATE TABLE users (
  id serial PRIMARY KEY not null,
  username VARCHAR(30) UNIQUE ,
  password VARCHAR(150)
);

INSERT INTO users(username, password) VALUES ('ceo','123');
INSERT INTO users(username, password) VALUES ('dto','123');
INSERT INTO users(username, password) VALUES ('mochilas','123');

drop table if exists employees;
create table employees(
  id serial PRIMARY KEY not null,
  name VARCHAR(150) not null,
  paternalName VARCHAR(150) not null,
  maternalName VARCHAR(150) not null,
  email VARCHAR(150) not null,
  userId int REFERENCES users(id)
);
insert into employees(name,paternalName,maternalName,email, userId) VALUES ('Mark Anthony','Arreguin','Gonzalez','ceo@opal.com',1);
insert into employees(name,paternalName,maternalName,email, userId) VALUES ('Richy','Gallegos','Gallegos','dto@opal.com',2);
insert into employees(name,paternalName,maternalName,email, userId) VALUES ('Cristian','Perez',':v','mochilas@opal.com',3);

-- Get: Employee Routes
select id,idPath from employees join routes on employees.id = routes.idEmployee;

-- Get employee routes
select idPath, latlong
from employees e join routes join customers c on routes.idCustomer = c.id on e.id = routes.idEmployee
where idEmployee = 1;


drop table if exists paths;
create table routes(
  idEmployee int not null references employees(id),
  idPath int not null,
  idCustomer int not null references customers(id),
  primary key(idEmployee,idCustomer,idPath)
);

insert into routes(idEmployee, idPath, idCustomer) VALUES (1,1,1);
insert into routes(idEmployee, idPath, idCustomer) VALUES (1,1,2);
insert into routes(idEmployee, idPath, idCustomer) VALUES (1,2,3);

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
  image bytea
);

insert into products(name, description) values ('Dona de chocolate','La dona llena de negro chocolate');
insert into products(name, description) values ('Dona de fresa','Dona llena de fresa');
insert into products(name, description) values ('Dona de coco','Dona con glaseado y pedazos de coco');
insert into products(name, description) values ('Dona de nuez','Dona con glaseado de nuez');
insert into products(name, description) values ('Dona glaseada','Una dona bien glaseada');
insert into products(name, description) values ('Dona de crema','Dona con mucha crema');
insert into products(name, description) values ('Donita','mini donita');
insert into products(name, description) values ('Donota','Una donota para los que tienen hambre');

drop table if exists productPrices;
create table productPrices(
  productId int references products(id),
  date date,
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
select id, name, description, image, price from products join productPrices on id=productId
where date = (SELECT
                  MAX(date)
                from productPrices
                where id = productId and date <= now());

drop table if exists stock;
create table stock(
  productId int references products(id) primary key ,
  quantity int
);

insert into stock(productId, quantity) values (1,10);
insert into stock(productId, quantity) values (2,10);
insert into stock(productId, quantity) values (3,10);
insert into stock(productId, quantity) values (4,10);
insert into stock(productId, quantity) values (5,10);
insert into stock(productId, quantity) values (6,10);
insert into stock(productId, quantity) values (7,10);
insert into stock(productId, quantity) values (8,10);

drop table if exists orders;
create table orders(
  id serial primary key not null,
  customerId int references customers(id),
  status boolean default false,
  orderDate date
);


select id, name, description from products join stock on products.id = stock.productId where quantity > 1;

insert into orders(customerId, orderDate) VALUES (1,now());
insert into customer_order(orderId, productId, quantity) VALUES (1,1,3);
insert into customer_order(orderId, productId, quantity) VALUES (1,2,3);
insert into customer_order(orderId, productId, quantity) VALUES (1,3,3);
insert into customer_order(orderId, productId, quantity) VALUES (1,4,3);


select * from orders join customer_order on orders.id = customer_order.orderId;

drop table if exists customer_order;
create table customer_order(
  orderId int references orders(id),
  productId int references products(id),
  quantity int not null,
  primary key (orderId,productId)
);

