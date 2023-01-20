create database loja_virtual;

use loja_virtual;

create table PRODUTO(
id int auto_increment,
nome varchar(50) not null,
descricao varchar(255),
primary key (id)
);

insert into produto(nome, descricao) values ('Notebook', 'Notebook Samsung');
insert into produto(nome, descricao) values ('Geladeira', 'Geladeira azul');

select * from produto;

show processlist;

create table CATEGORIA(
id int auto_increment,
nome varchar(50) not null,
primary key (id)
);

insert into categoria(nome) values ('ELETRONICOS');
insert into categoria(nome) values ('ELETRODOMESTICOS');
insert into categoria(nome) values ('MOVEIS');

select * from categoria;

alter table produto add column categoria_id int;

alter table produto add foreign key (categoria_id) references categoria (id);

update produto set categoria_id = 3 where id = 20;

select * from categoria c inner join
	produto p on c.id = p.categoria_id;