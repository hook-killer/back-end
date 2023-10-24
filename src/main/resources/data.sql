select 1 from dual;

insert into tbl_board (name, board_type, description) values("한국", "KOREA", "한국어게시판");
insert into tbl_board (name, board_type, description) values("일본", "JAPAN", "일본어게시판");
insert into tbl_board (name, board_type, description) values("중국", "CHINA", "중국어게시판");

-- insert into tbl_user(is_deleted, created_at, id, login_at, update_at, uuid_id, email, login_type, nick_name, password,
--                      role, status, thumbnail)
-- values (false, now(), now(), now(), now(), 1, 'bons', 'GOOGLE', 'ddd', 'ddd', 'ADMIN', 'ACTIVE', 'ADFASF');
--
-- insert into tbl_user_login_uuid(expriation_at, id, user_id, uuid_key)
-- values (now(), 1, 1, 'adsfasdf');
-- Password 11111111
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("admin@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "관리자", "ADMIN", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("admin2@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "관리자2", "ADMIN", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("admin3@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "관리자3", "ADMIN", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("admin4@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "관리자4", "ADMIN", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("admin5@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "관리자5", "ADMIN", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("admin6@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "관리자6", "ADMIN", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("admin7@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "관리자7", "ADMIN", "DEFAULT", "ACTIVE");

insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user1@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자1", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user2@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자2", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user3@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자3", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user4@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자4", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user5@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자5", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user6@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자6", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user7@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자7", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user8@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자8", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user9@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자9", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user10@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자10", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user11@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자11", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user12@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자12", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user13@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자13", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user14@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자14", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user15@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자15", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user16@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자16", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user17@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자17", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user18@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자18", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user19@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자19", "USER", "DEFAULT", "ACTIVE");
insert into tbl_user (email, password, nick_name, role, login_type, status) values ("user20@test.com", "$2a$10$dGUjr0mYEzEl55j8J0eJdeGHtPtIYENiQj04hVrscU37bySJl4Bla", "사용자20", "USER", "DEFAULT", "ACTIVE");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-20 10:00:00", 1, "2023-09-21 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (1, "KO", "title1", "content1");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-21 10:00:00", 1, "2023-09-22 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (2, "KO", "title2", "content2");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-22 10:00:00", 1, "2023-09-23 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (3, "KO", "title3", "content3");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-23 10:00:00", 1, "2023-09-24 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (4, "KO", "title4", "content4");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-24 10:00:00", 1, "2023-09-25 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (5, "KO", "title5", "content5");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-25 10:00:00", 1, "2023-09-26 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (6, "KO", "title6", "content6");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-26 10:00:00", 1, "2023-09-27 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (7, "KO", "title7", "content7");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-27 10:00:00", 1, "2023-09-28 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (8, "KO", "title8", "content8");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-28 10:00:00", 1, "2023-09-28 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (9, "KO", "title9", "content9");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-09-29 10:00:00", 1, "2023-09-30 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (10, "KO", "title10", "content10");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-01 10:00:00", 1, "2023-08-02 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (11, "KO", "title11", "content11");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-02 10:00:00", 1, "2023-08-03 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (12, "KO", "title12", "content12");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-03 10:00:00", 1, "2023-08-04 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (13, "KO", "title13", "content13");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-04 10:00:00", 1, "2023-08-05 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (14, "KO", "title14", "content14");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-05 10:00:00", 1, "2023-08-06 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (15, "KO", "title15", "content15");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-06 10:00:00", 1, "2023-08-07 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (16, "KO", "title16", "content16");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-08 10:00:00", 1, "2023-08-09 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (17, "KO", "title17", "content17");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-10 10:00:00", 1, "2023-08-11 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (18, "KO", "title18", "content18");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-12 10:00:00", 1, "2023-08-13 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (19, "KO", "title19", "content19");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-13 10:00:00", 1, "2023-08-14 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (20, "KO", "title20", "content20");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-15 10:00:00", 1, "2023-08-16 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (21, "KO", "title21", "content21");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-17 10:00:00", 1, "2023-08-18 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (22, "KO", "title22", "content22");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-19 10:00:00", 1, "2023-08-20 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (23, "KO", "title23", "content23");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-21 10:00:00", 1, "2023-08-22 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (24, "KO", "title24", "content24");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-23 10:00:00", 1, "2023-08-24 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (25, "KO", "title25", "content25");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-25 10:00:00", 1, "2023-08-26 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (26, "KO", "title26", "content26");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-27 10:00:00", 1, "2023-08-28 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (27, "KO", "title27", "content27");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-29 10:00:00", 1, "2023-08-30 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (28, "KO", "title28", "content28");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-08-31 10:00:00", 1, "2023-07-01 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (29, "KO", "title29", "content29");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-02 10:00:00", 1, "2023-07-03 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (30, "KO", "title30", "content30");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-04 10:00:00", 1, "2023-07-05 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (31, "KO", "title31", "content31");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-06 10:00:00", 1, "2023-07-07 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (32, "KO", "title32", "content32");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-08 10:00:00", 1, "2023-07-09 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (33, "KO", "title33", "content33");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-10 10:00:00", 1, "2023-07-11 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (34, "KO", "title34", "content34");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-12 10:00:00", 1, "2023-07-13 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (35, "KO", "title35", "content35");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-14 10:00:00", 1, "2023-07-15 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (36, "KO", "title36", "content36");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-16 10:00:00", 1, "2023-07-17 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (37, "KO", "title37", "content37");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-18 10:00:00", 1, "2023-07-19 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (38, "KO", "title38", "content38");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-20 10:00:00", 1, "2023-07-21 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (39, "KO", "title39", "content39");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-22 10:00:00", 1, "2023-07-23 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (40, "KO", "title40", "content40");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-24 10:00:00", 1, "2023-07-25 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (41, "KO", "title41", "content41");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-26 10:00:00", 1, "2023-07-27 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (42, "KO", "title42", "content42");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-07-28 10:00:00", 1, "2023-07-29 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (43, "KO", "title43", "content43");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-06-01 10:00:00", 1, "2023-06-02 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (44, "KO", "title44", "content44");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-06-02 10:00:00", 1, "2023-06-03 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (45, "KO", "title45", "content45");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-06-04 10:00:00", 1, "2023-06-05 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (46, "KO", "title46", "content46");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-06-06 10:00:00", 1, "2023-06-07 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (47, "KO", "title47", "content47");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-06-08 10:00:00", 1, "2023-06-09 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (48, "KO", "title48", "content48");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-06-10 10:00:00", 1, "2023-06-11 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (49, "KO", "title49", "content49");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-06-12 10:00:00", 1, "2023-06-13 10:00:00", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (50, "KO", "title50", "content50");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-22 19:03:38", 1, "2023-10-23 19:03:38", 1);
insert into tbl_notice_content (notice_id, language, title, content) values (51, "KO", "응애응애", "<div>응애응애응애 너무 일이 많아요</div>");
insert into tbl_notice_content (notice_id, language, title, content) values (51, "CN", "唧唧喳喳", "<div>嗯嗯嗯 有太多事情了</div>");
insert into tbl_notice_content (notice_id, language, title, content) values (51, "EN", "Crying", "<div>There are so many things going on.</div>");
insert into tbl_notice_content (notice_id, language, title, content) values (51, "JP", "応愛応愛", "<div>おぎゃあおぎゃあおぎゃあとてもお仕事が多いです</div>");


insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (5, "KO", 1, '2023-08-17 20:24:19', 1, '2023-08-17 20:24:19', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (1,'JP', 'タイトルです~', 'キングジョンウォンが占領した！');
insert into tbl_article_content (article_id, language, title, content) values (1,'CN', '是题目~', 'King钟元占领了！');
insert into tbl_article_content (article_id, language, title, content) values (1,'EN', 'It''s the title', 'King Jongwon has taken over!');
insert into tbl_article_content (article_id, language, title, content) values (1,'KO', '제목입니다~', '킹종원이 점령했다!');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 1, '2023-08-17 20:24:41', 1, '2023-08-17 20:24:41', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (2,'KO', '응애응애', '엉엉엉어엉엉엉');
insert into tbl_article_content (article_id, language, title, content) values (2,'JP', '応愛応愛', 'わんわんわんわんわんわんわんわん');
insert into tbl_article_content (article_id, language, title, content) values (2,'CN', '唧唧喳喳', '呜呜呜呜呜呜呜');
insert into tbl_article_content (article_id, language, title, content) values (2,'EN', 'Crying', '(Crying)');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 2, '2023-08-17 20:24:56', 1, '2023-08-17 20:24:56', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (3,'KO', '근우야!!!!!', '잘하자');
insert into tbl_article_content (article_id, language, title, content) values (3, 'JP', 'グンウ！！！！！', '頑張ろう');
insert into tbl_article_content (article_id, language, title, content) values (3, 'CN', '根宇！！！！！', '好好做吧。');
insert into tbl_article_content (article_id, language, title, content) values (3, 'EN', 'Geunwoo!!!!!', 'Let''s do well.');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 2, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (4, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (4, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (4, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (4, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 3, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (5, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (5, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (5, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (5, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:26:00', 1, '2023-08-17 20:26:00', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (6, 'KO', '미쯔야~', '<b>사랑해 ~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (6, 'JP', 'ミツヤ~', '<b>愛してる~~~~~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (6, 'CN', '米子呀~', '<b>爱你~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (6, 'EN', 'It''s Mitzu', '<b>I love you!</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-18 11:54:24', 1, '2023-08-18 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (7, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (7, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (7, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (7, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-19 11:54:24', 1, '2023-08-19 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (8, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (8, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (8, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (8, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-20 11:54:24', 1, '2023-08-20 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (9, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (9, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (9, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (9, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-21 11:54:24', 1, '2023-08-21 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (10, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (10, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (10, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (10, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-22 11:54:24', 1, '2023-08-22 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (11, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (11, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (11, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (11, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-23 11:54:24', 1, '2023-08-23 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (12, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (12, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (12, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (12, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-24 11:54:24', 1, '2023-08-24 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (13, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (13, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (13, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (13, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-25 11:54:24', 1, '2023-08-25 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (14, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (14, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (14, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (14, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-26 11:54:24', 1, '2023-08-26 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (15, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (15, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (15, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (15, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-27 11:54:24', 1, '2023-08-27 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (16, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (16, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (16, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (16, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-28 11:54:24', 1, '2023-08-28 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (17, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (17, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (17, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (17, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (5, "KO", 1, '2023-08-17 20:24:19', 1, '2023-08-17 20:24:19', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (18,'JP', 'タイトルです~', 'キングジョンウォンが占領した！');
insert into tbl_article_content (article_id, language, title, content) values (18,'CN', '是题目~', 'King钟元占领了！');
insert into tbl_article_content (article_id, language, title, content) values (18,'EN', 'It''s the title', 'King Jongwon has taken over!');
insert into tbl_article_content (article_id, language, title, content) values (18,'KO', '제목입니다~', '킹종원이 점령했다!');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 1, '2023-08-17 20:24:41', 1, '2023-08-17 20:24:41', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (19,'KO', '응애응애', '엉엉엉어엉엉엉');
insert into tbl_article_content (article_id, language, title, content) values (19,'JP', '応愛応愛', 'わんわんわんわんわんわんわんわん');
insert into tbl_article_content (article_id, language, title, content) values (19,'CN', '唧唧喳喳', '呜呜呜呜呜呜呜');
insert into tbl_article_content (article_id, language, title, content) values (19,'EN', 'Crying', '(Crying)');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 2, '2023-08-17 20:24:56', 1, '2023-08-17 20:24:56', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (20,'KO', '근우야!!!!!', '잘하자');
insert into tbl_article_content (article_id, language, title, content) values (20, 'JP', 'グンウ！！！！！', '頑張ろう');
insert into tbl_article_content (article_id, language, title, content) values (20, 'CN', '根宇！！！！！', '好好做吧。');
insert into tbl_article_content (article_id, language, title, content) values (20, 'EN', 'Geunwoo!!!!!', 'Let''s do well.');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 2, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (21, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (21, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (21, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (21, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 3, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (22, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (22, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (22, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (22, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:26:00', 1, '2023-08-17 20:26:00', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (23, 'KO', '미쯔야~', '<b>사랑해 ~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (23, 'JP', 'ミツヤ~', '<b>愛してる~~~~~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (23, 'CN', '米子呀~', '<b>爱你~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (23, 'EN', 'It''s Mitzu', '<b>I love you!</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-18 11:54:24', 1, '2023-08-18 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (24, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (24, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (24, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (24, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-22 11:54:24', 1, '2023-08-22 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (25, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (25, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (25, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (25, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-23 11:54:24', 1, '2023-08-23 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (26, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (26, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (26, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (26, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-24 11:54:24', 1, '2023-08-24 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (27, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (27, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (27, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (27, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-25 11:54:24', 1, '2023-08-25 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (28, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (28, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (28, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (28, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-26 11:54:24', 1, '2023-08-26 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (29, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (29, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (29, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (29, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-27 11:54:24', 1, '2023-08-27 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (30, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (30, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (30, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (30, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-28 11:54:24', 1, '2023-08-28 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (31, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (31, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (31, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (31, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (5, "KO", 1, '2023-08-17 20:24:19', 1, '2023-08-17 20:24:19', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (32,'JP', 'タイトルです~', 'キングジョンウォンが占領した！');
insert into tbl_article_content (article_id, language, title, content) values (32,'CN', '是题目~', 'King钟元占领了！');
insert into tbl_article_content (article_id, language, title, content) values (32,'EN', 'It''s the title', 'King Jongwon has taken over!');
insert into tbl_article_content (article_id, language, title, content) values (32,'KO', '제목입니다~', '킹종원이 점령했다!');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 1, '2023-08-17 20:24:41', 1, '2023-08-17 20:24:41', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (33,'KO', '응애응애', '엉엉엉어엉엉엉');
insert into tbl_article_content (article_id, language, title, content) values (33,'JP', '応愛応愛', 'わんわんわんわんわんわんわんわん');
insert into tbl_article_content (article_id, language, title, content) values (33,'CN', '唧唧喳喳', '呜呜呜呜呜呜呜');
insert into tbl_article_content (article_id, language, title, content) values (33,'EN', 'Crying', '(Crying)');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 2, '2023-08-17 20:24:56', 1, '2023-08-17 20:24:56', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (34,'KO', '근우야!!!!!', '잘하자');
insert into tbl_article_content (article_id, language, title, content) values (34, 'JP', 'グンウ！！！！！', '頑張ろう');
insert into tbl_article_content (article_id, language, title, content) values (34, 'CN', '根宇！！！！！', '好好做吧。');
insert into tbl_article_content (article_id, language, title, content) values (34, 'EN', 'Geunwoo!!!!!', 'Let''s do well.');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 2, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (35, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (35, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (35, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (35, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 3, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (36, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (36, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (36, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (36, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:26:00', 1, '2023-08-17 20:26:00', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (37, 'KO', '미쯔야~', '<b>사랑해 ~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (37, 'JP', 'ミツヤ~', '<b>愛してる~~~~~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (37, 'CN', '米子呀~', '<b>爱你~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (37, 'EN', 'It''s Mitzu', '<b>I love you!</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-18 11:54:24', 1, '2023-08-18 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (38, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (38, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (38, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (38, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-22 11:54:24', 1, '2023-08-22 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (39, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (39, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (39, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (39, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-23 11:54:24', 1, '2023-08-23 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (40, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (40, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (40, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (40, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-24 11:54:24', 1, '2023-08-24 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (41, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (41, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (41, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (41, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-25 11:54:24', 1, '2023-08-25 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (42, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (42, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (42, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (42, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-26 11:54:24', 1, '2023-08-26 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (43, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (43, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (43, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (43, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-27 11:54:24', 1, '2023-08-27 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (44, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (44, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (44, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (44, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-28 11:54:24', 1, '2023-08-28 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (45, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (45, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (45, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (45, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (5, "KO", 1, '2023-08-17 20:24:19', 1, '2023-08-17 20:24:19', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (46,'JP', 'タイトルです~', 'キングジョンウォンが占領した！');
insert into tbl_article_content (article_id, language, title, content) values (46,'CN', '是题目~', 'King钟元占领了！');
insert into tbl_article_content (article_id, language, title, content) values (46,'EN', 'It''s the title', 'King Jongwon has taken over!');
insert into tbl_article_content (article_id, language, title, content) values (46,'KO', '제목입니다~', '킹종원이 점령했다!');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 1, '2023-08-17 20:24:41', 1, '2023-08-17 20:24:41', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (47,'KO', '응애응애', '엉엉엉어엉엉엉');
insert into tbl_article_content (article_id, language, title, content) values (47,'JP', '応愛応愛', 'わんわんわんわんわんわんわんわん');
insert into tbl_article_content (article_id, language, title, content) values (47,'CN', '唧唧喳喳', '呜呜呜呜呜呜呜');
insert into tbl_article_content (article_id, language, title, content) values (47,'EN', 'Crying', '(Crying)');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 2, '2023-08-17 20:24:56', 1, '2023-08-17 20:24:56', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (48,'KO', '근우야!!!!!', '잘하자');
insert into tbl_article_content (article_id, language, title, content) values (48, 'JP', 'グンウ！！！！！', '頑張ろう');
insert into tbl_article_content (article_id, language, title, content) values (48, 'CN', '根宇！！！！！', '好好做吧。');
insert into tbl_article_content (article_id, language, title, content) values (48, 'EN', 'Geunwoo!!!!!', 'Let''s do well.');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 2, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (49, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (49, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (49, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (49, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 3, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (50, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (50, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (50, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (50, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:26:00', 1, '2023-08-17 20:26:00', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (51, 'KO', '미쯔야~', '<b>사랑해 ~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (51, 'JP', 'ミツヤ~', '<b>愛してる~~~~~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (51, 'CN', '米子呀~', '<b>爱你~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (51, 'EN', 'It''s Mitzu', '<b>I love you!</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-18 11:54:24', 1, '2023-08-18 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (52, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (52, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (52, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (52, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-22 11:54:24', 1, '2023-08-22 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (53, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (53, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (53, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (53, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-23 11:54:24', 1, '2023-08-23 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (54, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (54, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (54, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (54, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-24 11:54:24', 1, '2023-08-24 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (55, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (55, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (55, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (55, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-25 11:54:24', 1, '2023-08-25 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (56, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (56, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (56, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (56, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-26 11:54:24', 1, '2023-08-26 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (57, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (57, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (57, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (57, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-27 11:54:24', 1, '2023-08-27 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (58, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (58, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (58, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (58, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-28 11:54:24', 1, '2023-08-28 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (59, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (59, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (59, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (59, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:26:00', 1, '2023-08-17 20:26:00', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (60, 'KO', '미쯔야~', '<b>사랑해 ~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (60, 'JP', 'ミツヤ~', '<b>愛してる~~~~~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (60, 'CN', '米子呀~', '<b>爱你~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (60, 'EN', 'It''s Mitzu', '<b>I love you!</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-18 11:54:24', 1, '2023-08-18 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (61, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (61, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (61, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (61, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-22 11:54:24', 1, '2023-08-22 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (62, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (62, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (62, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (62, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-23 11:54:24', 1, '2023-08-23 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (63, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (63, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (63, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (63, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-24 11:54:24', 1, '2023-08-24 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (64, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (64, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (64, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (64, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-25 11:54:24', 1, '2023-08-25 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (65, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (65, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (65, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (65, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-26 11:54:24', 1, '2023-08-26 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (66, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (66, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (66, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (66, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-27 11:54:24', 1, '2023-08-27 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (67, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (67, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (67, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (67, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-28 11:54:24', 1, '2023-08-28 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (68, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (68, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (68, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (68, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (5, "KO", 1, '2023-08-17 20:24:19', 1, '2023-08-17 20:24:19', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (69,'JP', 'タイトルです~', 'キングジョンウォンが占領した！');
insert into tbl_article_content (article_id, language, title, content) values (69,'CN', '是题目~', 'King钟元占领了！');
insert into tbl_article_content (article_id, language, title, content) values (69,'EN', 'It''s the title', 'King Jongwon has taken over!');
insert into tbl_article_content (article_id, language, title, content) values (69,'KO', '제목입니다~', '킹종원이 점령했다!');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 1, '2023-08-17 20:24:41', 1, '2023-08-17 20:24:41', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (70,'KO', '응애응애', '엉엉엉어엉엉엉');
insert into tbl_article_content (article_id, language, title, content) values (70,'JP', '応愛応愛', 'わんわんわんわんわんわんわんわん');
insert into tbl_article_content (article_id, language, title, content) values (70,'CN', '唧唧喳喳', '呜呜呜呜呜呜呜');
insert into tbl_article_content (article_id, language, title, content) values (70,'EN', 'Crying', '(Crying)');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (1, "KO", 2, '2023-08-17 20:24:56', 1, '2023-08-17 20:24:56', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (71,'KO', '근우야!!!!!', '잘하자');
insert into tbl_article_content (article_id, language, title, content) values (71, 'JP', 'グンウ！！！！！', '頑張ろう');
insert into tbl_article_content (article_id, language, title, content) values (71, 'CN', '根宇！！！！！', '好好做吧。');
insert into tbl_article_content (article_id, language, title, content) values (71, 'EN', 'Geunwoo!!!!!', 'Let''s do well.');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 2, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (72, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (72, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (72, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (72, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 3, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (73, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (73, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (73, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (73, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:26:00', 1, '2023-08-17 20:26:00', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (74, 'KO', '미쯔야~', '<b>사랑해 ~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (74, 'JP', 'ミツヤ~', '<b>愛してる~~~~~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (74, 'CN', '米子呀~', '<b>爱你~~~~~~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (74, 'EN', 'It''s Mitzu', '<b>I love you!</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-18 11:54:24', 1, '2023-08-18 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (75, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (75, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (75, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (75, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-22 11:54:24', 1, '2023-08-22 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (76, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (76, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (76, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (76, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-23 11:54:24', 1, '2023-08-23 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (77, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (77, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (77, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (77, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-24 11:54:24', 1, '2023-08-24 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (78, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (78, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (78, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (78, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-25 11:54:24', 1, '2023-08-25 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (79, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (79, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (79, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (79, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-26 11:54:24', 1, '2023-08-26 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (80, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (80, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (80, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (80, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-27 11:54:24', 1, '2023-08-27 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (81, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (81, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (81, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (81, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count ,board_id, article_status, org_article_language, created_user_id, created_at, updated_user_id, update_at) values (0, 1, 'PUBLIC', 'KO', 1, '2023-08-28 11:54:24', 1, '2023-08-28 11:54:24');
insert into tbl_article_content (article_id, language, title, content) values (82, 'KO', '너무귀여운 우리 응애미쯔', '<b>사랑해 ~~~~~~~알라쀼~~~</b>');
insert into tbl_article_content (article_id, language, title, content) values (82, 'EN', 'It''s Mitzu', '<b>I love you! I love you!</b>');
insert into tbl_article_content (article_id, language, title, content) values (82, 'CN', '米子呀~', '<b>我爱你~~~~~~~~阿拉法乌。</b>');
insert into tbl_article_content (article_id, language, title, content) values (82, 'JP', 'ミツヤ~', '<b>愛してる~~~~~アラピュ~~~</b>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (83, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (83, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (83, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (83, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (84, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (84, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (84, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (84, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (85, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (85, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (85, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (85, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (86, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (86, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (86, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (86, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (87, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (87, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (87, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (87, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (88, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (88, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (88, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (88, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (89, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (89, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (89, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (89, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (90, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (90, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (90, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (90, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (91, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (91, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (91, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (91, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (92, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (92, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (92, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (92, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (93, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (93, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (93, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (93, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (94, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (94, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (94, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (94, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:20', 1, '2023-08-17 20:25:20', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (95, 'KO', '오리엔탈', '<div>유니티</div>');
insert into tbl_article_content (article_id, language, title, content) values (95, 'EN', 'Oriental', '<div>UNI.T.</div>');
insert into tbl_article_content (article_id, language, title, content) values (95, 'CN', '东方', '<div>男女通用</div>');
insert into tbl_article_content (article_id, language, title, content) values (95, 'JP', 'オリエンタル', '<div>ユニティ</div>');

insert into tbl_article (like_count, org_article_language, board_id, created_at, created_user_id, update_at, updated_user_id, article_status) values (0, "KO", 1, '2023-08-17 20:25:36', 1, '2023-08-17 20:25:36', 1, 'PUBLIC');
insert into tbl_article_content (article_id, language, title, content) values (96, 'KO', '엘라스틴했어요', '<p>배고파요 흑흑흑</p>');
insert into tbl_article_content (article_id, language, title, content) values (96, 'JP', 'エラスチンしました', '<p>お腹すきました。フフフフ</p>');
insert into tbl_article_content (article_id, language, title, content) values (96, 'CN', '弹力弹力了', '<p>好饿啊 呜呜呜</p>');
insert into tbl_article_content (article_id, language, title, content) values (96, 'EN', 'It was Elastin', '<p>I''m hungry.</p>');


insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (1, "KO", 'USABLE', '2023-08-17 21:01:44', 1, '2023-08-17 21:01:44', 1);
insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (1, "KO", 'USABLE', '2023-08-17 21:04:05', 1, '2023-08-17 21:04:05', 1);
insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (2, "KO", 'USABLE', '2023-08-17 21:04:13', 1, '2023-08-17 21:04:13', 1);
insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (3, "KO", 'USABLE', '2023-08-17 21:04:21', 1, '2023-08-17 21:04:21', 1);
insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (4, "KO", 'USABLE', '2023-08-17 21:04:30', 1, '2023-08-17 21:04:30', 1);
insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (5, "KO", 'USABLE', '2023-08-17 21:05:00', 1, '2023-08-17 21:05:00', 1);
insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (5, "KO", 'USABLE', '2023-08-17 21:05:20', 1, '2023-08-17 21:05:20', 1);
insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (5, "EN", 'USABLE', '2023-08-17 21:05:33', 1, '2023-08-17 21:05:33', 1);


insert into tbl_reply_content (reply_id, language, content) values (1, "KO", '안녕하세요');
insert into tbl_reply_content (reply_id, language, content) values (1, "EN", 'Hello');
insert into tbl_reply_content (reply_id, language, content) values (1, "CN", '你好。');
insert into tbl_reply_content (reply_id, language, content) values (1, "JP", 'こんにちは');

insert into tbl_reply_content (reply_id, language, content) values (2, "KO", '응애예요~~~');
insert into tbl_reply_content (reply_id, language, content) values (2, "EN", 'I''m crying');
insert into tbl_reply_content (reply_id, language, content) values (2, "CN", '是嘤嘤~~~');
insert into tbl_reply_content (reply_id, language, content) values (2, "JP", 'おぎゃあです~~~');

insert into tbl_reply_content (reply_id, language, content) values (3, "KO", '엄마 사랑해~');
insert into tbl_reply_content (reply_id, language, content) values (3, "EN", 'Mom, I love you');
insert into tbl_reply_content (reply_id, language, content) values (3, "CN", '妈妈我爱你~');
insert into tbl_reply_content (reply_id, language, content) values (3, "JP", 'お母さん愛してる~');

insert into tbl_reply_content (reply_id, language, content) values (4, "KO", '비트캠프가 싫어요~');
insert into tbl_reply_content (reply_id, language, content) values (4, "EN", 'I don''t like beat camp');
insert into tbl_reply_content (reply_id, language, content) values (4, "CN", '讨厌Beat Camp~');
insert into tbl_reply_content (reply_id, language, content) values (4, "JP", 'ビートキャンプが嫌いです~');

insert into tbl_reply_content (reply_id, language, content) values (5, "KO", '여기 사람있어요ㅠㅠㅠㅠ');
insert into tbl_reply_content (reply_id, language, content) values (5, "EN", 'There''s a person here(crying)');
insert into tbl_reply_content (reply_id, language, content) values (5, "CN", '这里有人呜呜呜呜');
insert into tbl_reply_content (reply_id, language, content) values (5, "JP", 'ここに人がいますㅠㅠㅠㅠㅠㅠ');

insert into tbl_reply_content (reply_id, language, content) values (6, "KO", '코드싸개가 흑흑흑');
insert into tbl_reply_content (reply_id, language, content) values (6, "EN", 'The code wrap');
insert into tbl_reply_content (reply_id, language, content) values (6, "CN", '电线包 呜呜呜');
insert into tbl_reply_content (reply_id, language, content) values (6, "JP", 'コード包みがフフフフ');

insert into tbl_reply_content (reply_id, language, content) values (7, "KO", '뿌애애애애애애앵');
insert into tbl_reply_content (reply_id, language, content) values (7, "EN", 'Crying. Crying');
insert into tbl_reply_content (reply_id, language, content) values (7, "CN", '扑哧扑哧');
insert into tbl_reply_content (reply_id, language, content) values (7, "JP", 'プエエエエエエエエアン');

insert into tbl_reply_content (reply_id, language, content) values (8, "KO", '나는 매우 슬프다');
insert into tbl_reply_content (reply_id, language, content) values (8, "EN", 'I''m Very Sad');
insert into tbl_reply_content (reply_id, language, content) values (8, "CN", '我很伤心');
insert into tbl_reply_content (reply_id, language, content) values (8, "JP", '私はとても悲しい');

insert into tbl_reply (article_id, org_reply_language, reply_status, created_at, created_user_id, update_at, updated_user_id) values (7, 'KO', 'USABLE', '2023-08-18 13:17:02', 1, '2023-08-18 13:17:02', 1);
insert into tbl_reply_content ( reply_id, language, content) values (9, 'KO', '응애응애 너무 졸린 점심이에요~~~~');
insert into tbl_reply_content ( reply_id, language, content) values (9, 'CN', '嗯嗯，中午太困了~~~~');
insert into tbl_reply_content ( reply_id, language, content) values (9, 'EN', 'It''s such a sleepy lunch~~~');
insert into tbl_reply_content ( reply_id, language, content) values (9, 'JP', 'うんうん、とても眠いお昼です~~~~');



insert into tbl_article_like (article_id, user_id, created_at, update_at) values (2, 1, '2023-08-18 13:42:36', '2023-08-18 13:42:36');
insert into tbl_article_like (article_id, user_id, created_at, update_at) values (2, 2, '2023-08-18 13:42:36', '2023-08-18 13:42:36');
insert into tbl_article_like (article_id, user_id, created_at, update_at) values (2, 3, '2023-08-18 13:42:36', '2023-08-18 13:42:36');
insert into tbl_article_like (article_id, user_id, created_at, update_at) values (2, 4, '2023-08-18 13:42:36', '2023-08-18 13:42:36');
insert into tbl_article_like (article_id, user_id, created_at, update_at) values (2, 5, '2023-08-18 13:42:36', '2023-08-18 13:42:36');
insert into tbl_article_like (article_id, user_id, created_at, update_at) values (3, 1, '2023-08-18 13:42:36', '2023-08-18 13:42:36');
insert into tbl_article_like (article_id, user_id, created_at, update_at) values (4, 1, '2023-08-18 13:42:36', '2023-08-18 13:42:36');

