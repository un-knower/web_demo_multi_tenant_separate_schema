
create user sps with password 'sps';

create database demo_db owner sps;
grant all privileges on database demo_db to sps;