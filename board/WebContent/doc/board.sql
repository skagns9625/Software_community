create table board1(
  num int(20) AUTO_INCREMENT primary key,-- 번호
  id varchar(20),-- 아이디 
  subject varchar(200), -- 제목
  content varchar(200), -- 내용
  boarddate date, -- 작성일자
  score int(20), -- 조회 수
  category varchar(200) -- 카테고리
  );