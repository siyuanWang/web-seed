delete from sys_user;
delete from sys_menu;
delete from sys_role;
delete from sys_user_role;
delete from sys_role_menu;
--
-- Data for table public.sys_user (OID = 16416) (LIMIT 0,2)
--
INSERT INTO sys_user (id, login_name, password, name, phone, email, is_builtin, login_time, password_last_modify_time, update_time, create_time)
VALUES (1, 'administrator', '123456', 'wsy', '17601612994', 'wsy2355883@163.com', true, '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52');

INSERT INTO sys_user (id, login_name, password, name, phone, email, is_builtin, login_time, password_last_modify_time, update_time, create_time)
VALUES (1, 'admin', 'admin', 'wsy', '17601612994', 'wsy2355883@163.com', true, '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52', '2015-10-12 14:12:52');

--
-- Data for table public.sys_menu (OID = 16432) (LIMIT 0,6)
--
INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (1, 0, '系统管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (2, 0, '车辆管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (3, 0, '系统参数', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (4, 0, '日志管理', '#/log', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (101, 1, '用户管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (102, 1, '角色管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (103, 1, '机构管理', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (5, 0, '服务注册管理', '#/subscription', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (10101, 101, 'VIP用户', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');

INSERT INTO sys_menu (id, pid, name, url, order_num, type, update_time, create_time)
VALUES (10102, 101, '普通用户', 'javascript:void(0)', 1, 1, '2015-05-25 14:08:03', '2015-05-25 14:08:03');


insert into sys_role (id,name,is_builtin,update_time,create_time) VALUES (1, 'admin', true, now(), now());

delete from sys_role_menu;
insert into sys_role_menu (ID,ROLE_ID,MENU_ID,UPDATE_TIME,CREATE_TIME)
select nextval('geely_sequences'), 1, ID, now(), now() from sys_menu;

insert into sys_user_role (ID, USER_ID, ROLE_ID, UPDATE_TIME, CREATE_TIME) VALUES (1, 1, 1, now(), now());
