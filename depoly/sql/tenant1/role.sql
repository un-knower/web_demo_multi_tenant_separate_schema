
create table tenant1.role (
    id serial not null primary key,
    role_name varchar(255) not null
);


--表说明
COMMENT ON TABLE tenant1.role IS '角色表';
--字段说明
COMMENT ON COLUMN tenant1.role.id IS '主键自增';
COMMENT ON COLUMN tenant1.role.role_name IS '角色名';


insert into tenant1.role (role_name) values ('super admin');
insert into tenant1.role (role_name) values ('admin');
insert into tenant1.role (role_name) values ('user');
insert into tenant1.role (role_name) values ('audit');
