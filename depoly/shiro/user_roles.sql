

create table user_roles (
    id bigserial not null primary key,
    username varchar(255) not null,
    role_name varchar(255) not null

)

create unique index uniq_idx_user_roles on user_roles(username, role_name);
