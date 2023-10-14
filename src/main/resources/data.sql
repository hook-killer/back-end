select 1 from dual;

-- insert into tbl_user(is_deleted, created_at, id, login_at, update_at, uuid_id, email, login_type, nick_name, password,
--                      role, status, thumbnail)
-- values (false, now(), now(), now(), now(), 1, 'bons', 'GOOGLE', 'ddd', 'ddd', 'ADMIN', 'ACTIVE', 'ADFASF');
--
-- insert into tbl_user_login_uuid(expriation_at, id, user_id, uuid_key)
-- values (now(), 1, 1, 'adsfasdf');

insert into tbl_user (email, password, nick_name, role) values ("admin@test.com", "11111111", "관리자", "ADMIN");
insert into tbl_user (email, password, nick_name, role) values ("user@test.com", "1111", "사용자", "USER");

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

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-01 10:00:00", 1, "2023-10-02 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (11, "KO", "title11", "content11");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-02 10:00:00", 1, "2023-10-03 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (12, "KO", "title12", "content12");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-03 10:00:00", 1, "2023-10-04 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (13, "KO", "title13", "content13");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-04 10:00:00", 1, "2023-10-05 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (14, "KO", "title14", "content14");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-05 10:00:00", 1, "2023-10-06 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (15, "KO", "title15", "content15");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-06 10:00:00", 1, "2023-10-07 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (16, "KO", "title16", "content16");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-08 10:00:00", 1, "2023-10-09 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (17, "KO", "title17", "content17");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-10 10:00:00", 1, "2023-10-11 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (18, "KO", "title18", "content18");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-12 10:00:00", 1, "2023-10-13 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (19, "KO", "title19", "content19");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-13 10:00:00", 1, "2023-10-14 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (20, "KO", "title20", "content20");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-15 10:00:00", 1, "2023-10-16 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (21, "KO", "title21", "content21");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-17 10:00:00", 1, "2023-10-18 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (22, "KO", "title22", "content22");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-19 10:00:00", 1, "2023-10-20 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (23, "KO", "title23", "content23");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-21 10:00:00", 1, "2023-10-22 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (24, "KO", "title24", "content24");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-23 10:00:00", 1, "2023-10-24 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (25, "KO", "title25", "content25");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-25 10:00:00", 1, "2023-10-26 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (26, "KO", "title26", "content26");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-27 10:00:00", 1, "2023-10-28 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (27, "KO", "title27", "content27");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-29 10:00:00", 1, "2023-10-30 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (28, "KO", "title28", "content28");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-10-31 10:00:00", 1, "2023-11-01 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (29, "KO", "title29", "content29");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-02 10:00:00", 1, "2023-11-03 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (30, "KO", "title30", "content30");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-04 10:00:00", 1, "2023-11-05 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (31, "KO", "title31", "content31");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-06 10:00:00", 1, "2023-11-07 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (32, "KO", "title32", "content32");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-08 10:00:00", 1, "2023-11-09 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (33, "KO", "title33", "content33");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-10 10:00:00", 1, "2023-11-11 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (34, "KO", "title34", "content34");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-12 10:00:00", 1, "2023-11-13 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (35, "KO", "title35", "content35");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-14 10:00:00", 1, "2023-11-15 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (36, "KO", "title36", "content36");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-16 10:00:00", 1, "2023-11-17 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (37, "KO", "title37", "content37");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-18 10:00:00", 1, "2023-11-19 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (38, "KO", "title38", "content38");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-20 10:00:00", 1, "2023-11-21 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (39, "KO", "title39", "content39");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-22 10:00:00", 1, "2023-11-23 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (40, "KO", "title40", "content40");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-24 10:00:00", 1, "2023-11-25 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (41, "KO", "title41", "content41");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-26 10:00:00", 1, "2023-11-27 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (42, "KO", "title42", "content42");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-11-28 10:00:00", 1, "2023-11-29 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (43, "KO", "title43", "content43");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-12-01 10:00:00", 1, "2023-12-02 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (44, "KO", "title44", "content44");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-12-02 10:00:00", 1, "2023-12-03 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (45, "KO", "title45", "content45");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-12-04 10:00:00", 1, "2023-12-05 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (46, "KO", "title46", "content46");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-12-06 10:00:00", 1, "2023-12-07 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (47, "KO", "title47", "content47");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-12-08 10:00:00", 1, "2023-12-09 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (48, "KO", "title48", "content48");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-12-10 10:00:00", 1, "2023-12-11 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (49, "KO", "title49", "content49");

insert into tbl_notice_article (language, status, created_at, created_user_id, update_at, updated_user_id) values ("KO", "PUBLIC", "2023-12-12 10:00:00", 1, "2023-12-13 10:00:00", 1);

insert into tbl_notice_content (notice_id, language, title, content) values (50, "KO", "title50", "content50");

