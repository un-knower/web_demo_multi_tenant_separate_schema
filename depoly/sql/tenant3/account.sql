
create table tenant3.account (
	id serial not null primary key,
	user_name varchar(255) not null,
	password varchar(255) not null
);


--表说明  
COMMENT ON TABLE tenant3.account IS '用户表';
--字段说明 
COMMENT ON COLUMN tenant3.account.id IS '主键自增';
COMMENT ON COLUMN tenant3.account.user_name IS '用户名';
COMMENT ON COLUMN tenant3.account.password IS '密码';


create unique index unique_idx_account_user_name on tenant3.account(user_name);


--tenant3
insert into tenant3.account (user_name, password) values ('admin@tenant3.com', '123');
insert into tenant3.account (user_name, password) values ('guest@tenant3.com', '123');

