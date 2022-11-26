use loja_virtual;

create table PRODUTO(
id int auto_increment,
nome varchar(50) not null,
descricao varchar(255),
primary key (id)
);

insert into produto(nome, descricao) values ('Notebook', 'Notebook Samsung');

select * from produto;