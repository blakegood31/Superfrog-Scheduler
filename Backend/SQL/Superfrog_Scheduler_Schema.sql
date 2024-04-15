use Superfrog_Scheduler;

drop table request;
drop table customer;
drop table availability;
drop table spirit_calendar;
drop table student;
drop table director;

create table director(
    id int primary key not null,
    name varchar(25) not null
);

create table student(
    id VARCHAR(20) primary key not null,
    name varchar(20) not null,
    performance decimal(2, 1) not null
);

create table customer(
    id int primary key not null ,
    name varchar(25) not null ,
    email varchar(30) not null,
    phone int(9) not null
);

create table request(
    id VARCHAR(25) primary key not null,
    address varchar(30) not null ,
    description varchar(200) not null ,
    date DATETIME not null,
    event varchar(20) not null ,
    status varchar(10) not null ,
    sid VARCHAR(20) not null ,
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
    superfrog_id VARCHAR(25),
    foreign key (superfrog_id) references student(id)
);

create table availability(
    day DATE not null ,
    superfrog_id VARCHAR(25) not null,
    is_available bool not null,
    start_available DATETIME,
    end_available DATETIME,
    primary key (day, superfrog_id),
    foreign key (superfrog_id) references student(id)
);

insert into director values(1, 'John Doe');
insert into student values('1001100011', 'Super Frog', 4.3);

select user();

show variables;