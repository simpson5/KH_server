--===============================================
--관리자(system)계정
--===============================================

--web계정생성
create user pixar identified by pixar
default tablespace users;

--권한부여
grant connect, resource to pixar;

--1. 위의 2개 쿼리문 실행 후 접속에서 +눌러서 pixar접속 생성
--2. pixar_pixar.sql파일 열어서 pixar로 접속 후 쿼리문 실행
--3. select * from member; 실행 시 admin과 abcde회원 출력되어야 성공!!

--웹루트 pixar