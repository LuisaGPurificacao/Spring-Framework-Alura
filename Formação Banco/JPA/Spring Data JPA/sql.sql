use mudi2;

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
select * from oferta;

delete from authorities;
delete from users;

INSERT INTO `mudi2`.`pedido` (`data_da_entrega`, `descricao`, `nome_produto`, `url_imagem`, `url_produto`, `valor_negociado`, `status`, `user_username`) 
VALUES (
'2022-12-20', 
"Creme Hidratante Facial Anti-idade L'Oréal Paris Revitalift", 
'Creme hidratante', 
'https://m.media-amazon.com/images/I/61S7Q0vxA-S._AC_SX466_.jpg', 
'https://www.amazon.com.br/dp/B07VGF2P6L/ref=cm_gf_aaem_iaae_d_p0_e0_qd1_8uDzO3hEu3K6QYsTEvrh', 
'200', 
'AGUARDANDO', 
'maria');


DELETE FROM users WHERE username = 'maria';

commit;