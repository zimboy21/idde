create database idde;


use idde;

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'idde_zdim1981';
flush privileges;

create table if not exists cpu(
	id bigint primary key auto_increment,
    name varchar(50),
    price double,
    clockspeed double,
    overclocking integer,
    corecount integer
);

create table if not exists  motherboard(
	id bigint primary key auto_increment,
    name varchar(50),
    price double,
    fsb integer,
    bios varchar(50),
    memory integer,
    cpuid bigint,
    foreign key(cpuid) references cpu(id) on delete cascade
);

insert into cpu(name, price, clockspeed, overclocking, corecount) values("AMD Ryzen 7 5800X", 2000.2, 3.4, 1, 16);
insert into cpu(name, price, clockspeed, overclocking, corecount) values("Intel Core i9", 3540.2, 4.9, 1, 32);
insert into motherboard(name, price, fsb, bios, memory, cpuid) values("ASUS PTGD1-LA",  550, 800, "HP BIOS", 128 , 1);