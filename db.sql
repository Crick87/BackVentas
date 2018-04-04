DROP TABLE if exists users;
CREATE TABLE users (
  id int PRIMARY KEY auto_increment not null,
  username VARCHAR(30) UNIQUE ,
  password VARCHAR(150)
);

INSERT INTO users(username, password) VALUES ('ceo','123');
INSERT INTO users(username, password) VALUES ('dto','123');
INSERT INTO users(username, password) VALUES ('mochilas','123');

drop table if exists employees;
create table employees(
  id int PRIMARY KEY auto_increment not null,
  name VARCHAR(150) not null,
  paternalName VARCHAR(150) not null,
  maternalName VARCHAR(150) not null,
  birthday DATE ,
  email VARCHAR(150) not null,
  userId int REFERENCES users(id)
);

insert into employees(name,paternalName,maternalName,birthday,email, userId) VALUES ('Mark Anthony','Arreguin','Gonzalez','2008-7-04','ceo@opal.com',1);
insert into employees(name,paternalName,maternalName,birthday,email, userId) VALUES ('Richy','Gallegos','Gallegos','2008-7-04','dto@opal.com',2);
insert into employees(name,paternalName,maternalName,birthday,email, userId) VALUES ('Cristian','Perez',':v','2008-7-04','mochilas@opal.com',3);

drop table if exists token;
create TABLE token(
  id int PRIMARY KEY AUTO_INCREMENT not null,
  token VARCHAR(200) not null
);

drop table if exists customers;
create table customers (
  id int primary key auto_increment not null,
  name varchar(150) not null,
  phone varchar(150) not null,
  email varchar(150) not null
);

insert into customers(name, phone, email) value ('Cristian', '123123', 'creshandkesh@gmail.com');

drop table if exists products;
create table products(
  id int primary key auto_increment not null,
  name varchar(150) not null ,
  price int not null
);

insert into products(name, price) value ('Dona de chocolate',4);
insert into products(name, price) value ('Dona de fresa',4);
insert into products(name, price) value ('Dona de coco',5);
insert into products(name, price) value ('Dona de nuez',5);
insert into products(name, price) value ('Dona glaseada',6);
insert into products(name, price) value ('Dona de crema',10);
insert into products(name, price) value ('Donita',2);
insert into products(name, price) value ('Donota',12);

drop table if exists orders;
create table orders(
  id int primary key auto_increment not null,
  status boolean default false
);

drop table if exists customer_order;
create table customer_order(
  orderId int references orders(id),
  productId int references products(id),
  quantity int not null,
  primary key (orderId,productId)
);

