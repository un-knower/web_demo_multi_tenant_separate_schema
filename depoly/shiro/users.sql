
create table users(

  id bigserial not null primary key,
  username varchar(255) not null,
  password varchar(255) not null,
  password_salt varchar(100) not null

);

create unique index uniq_idx_users_username on users(username);