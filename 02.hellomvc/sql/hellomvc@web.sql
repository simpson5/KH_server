--================================================
-- 관리자계정
--================================================
-- web계정 생성
create user web
identified by web
default tablespace users;

-- 권한붜여
grant connect, resource to web;

--================================================
-- WEB계정
--================================================
show user;

-- 회원테이블 생성
create table member(
    member_id varchar2(15),
    password varchar2(300) not null,
    member_name varchar2(50) not null,
    member_role char(1) default 'U' not null,
    gender char(1),
    birthday date,
    email varchar2(100),
    phone char(11) not null,
    address varchar2(200),
    hobby varchar2(200),                                                              -- 운동, 등산, 독서, 게임, 여행
    enroll_date date default sysdate,
    constraint pk_member_id primary key(member_id),
    constraint ck_gender check(gender in ('M', 'F')),                       -- M 남자, F 여자
    constraint ck_member_role check(member_role in ('U','A'))         -- U 일반사용자, A 관리자
);

--회원 추가
	insert into member
	values (
		'honggd', '1234', '홍길동', 'U', 'M', to_date('20000909','yyyymmdd'),
		'honggd@naver.com', '01012341234', '서울시 강남구', '운동,등산,독서', default
	);
	insert into member
	values (
		'qwerty', '1234', '쿠어티', 'U', 'F', to_date('19900215','yyyymmdd'),
		'qwerty@naver.com', '01012341234', '서울시 송파구', '운동,등산', default
	);
	insert into member
	values (
		'admin', '1234', '관리자', 'A', 'M', to_date('19801010','yyyymmdd'),
		'admin@naver.com', '01056785678', '서울시 관악구', '게임,독서', default
	);
    
    commit;

--drop table member;

select * from member;

select * from member where member_id = 'honggd' and password = '1234';