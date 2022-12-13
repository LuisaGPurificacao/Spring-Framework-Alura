create database mudi;

use mudi;

create table users(
    username varchar(50) not null primary key,
    password varchar(200) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

select * from pedido;
select * from users;
select * from authorities;

delete from authorities;
delete from users;

DELETE FROM pedido WHERE ID = 12;

commit;