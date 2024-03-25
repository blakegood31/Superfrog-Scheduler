create database Superfrog_Scheduler;
use Superfrog_Scheduler;

create table student(
    id int primary key not null,
    sup_id int not null,
    name varchar(20) not null,
    performance decimal(1, 2) not null,
    foreign key (sup_id) references director(id)
);

create table director(
    id int primary key not null,
    name varchar(25) not null
);

create table customer(
    id int primary key not null ,
    name varchar(25) not null ,
    email varchar(30) not null,
    phone int(9) not null
);

create table request(
    id int primary key not null,
    address varchar(30) not null ,
    description varchar(200) not null ,
    date DATETIME not null,
    event varchar(20) not null ,
    status varchar(10) not null ,
    sid int not null ,
    cid int not null ,
    info varchar(200),
    other_orgs varchar(100),
    foreign key (sid) references student (id),
    foreign key (cid) references customer (id)
);

create table spirit_calendar(
    event_id int primary key ,
    start_time DATETIME not null ,
    end_time DATETIME not null ,
    superfrog_assigned bool not null ,
    superfrog_id int,
    foreign key (superfrog_id) references student(id)
);

create table availability(
    day DATE not null ,
    superfrog_id int not null,
    is_available bool not null,
    start_available DATETIME,
    end_available DATETIME,
    primary key (day, superfrog_id),
    foreign key (superfrog_id) references student(id)
);