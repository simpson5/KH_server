

create user simpson999
identified by simpson999
default tablespace users;

grant connect, resource to simpson999;

create table member(
    member_id varchar2(20),
    password varchar2(20) not null,
    member_name varchar2(100) not null,
    ssn varchar2(7) not null,
    phone char(11) not null,
    address varchar2(1000),
    email varchar2(200),
    enroll_date date default sysdate,
    constraint pk_member_id primary key(member_id)
);

--drop table member;

insert into member values('1', '1', '1', '1', '1', '1', '1', default);

select * from member;