drop database if exists SX;
create database SX;
use SX;

drop table if exists users;
create table users(
#   用户账号
    uid varchar(20) primary key,
    username varchar(30) not null,
    pwd varchar(15) not null,
    phone varchar(15) not null unique
);

drop table if exists house;
create table house(
    hid int primary key auto_increment,
    hDate date not null,
    location varchar(50) not null,
    title varchar(30) not null,
    price double not null,
    shape varchar(20) not null,
    area double not null,
    hPhone varchar(15) not null,
    constraint fk_hPhone foreign key (hPhone) references users(phone),
    description varchar(150) not null
);

