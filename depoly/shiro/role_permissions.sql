
create table role_permissions(
  id bigservial not null primary key,
  role_name varchar(255) not null,
  permission varchar(255) not null

);

create unique index uniq_idx_role_permission on role_permissions(role_name, permission);


