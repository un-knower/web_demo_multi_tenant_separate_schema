
create table tenant1.account (
	id serial not null primary key,
	user_name varchar(255) not null,
	password varchar(255) not null
);


--表说明  
COMMENT ON TABLE tenant1.account IS '用户表';
--字段说明 
COMMENT ON COLUMN tenant1.account.id IS '主键自增';
COMMENT ON COLUMN tenant1.account.user_name IS '用户名';
COMMENT ON COLUMN tenant1.account.password IS '密码';


create unique index unique_idx_account_user_name on tenant1.account(user_name);


--tenant1
insert into tenant1.account (user_name, password) values ('admin@tenant1.com', '123');
insert into tenant1.account (user_name, password) values ('guest@tenant1.com', '123');

