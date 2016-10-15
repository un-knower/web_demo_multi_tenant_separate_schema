
create user sps with password 'sps';

create database sps_db owner sps;
grant all privileges on database sps_db to sps;