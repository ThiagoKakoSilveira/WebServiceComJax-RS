create table produto(
    id serial primary key,
    nome varchar(20) not null,
    preco numeric not null
);

create table Endereco(
	id serial primary key,
	cidade varchar(50) not null,
	rua varchar(50) not null,
	numero integer not null,
	complemento varchar(10)
);

create table contato(
	id serial primary key,
	email VARCHAR(50),	
);

create table telefone(
	idTelefone serial primary key,
	contatoId bigInt references contato,
	prefixotel char(3) not null,
	numero varchar(9) not null
);

create table usuario(
	id serial primary key,
	nome varchar(30) not null,
	sobrenome varchar(60) not null,	
	documentoIdent varchar(14) not null,
	dataNasc date not null,
	enderecoId bigInt references endereco,
	contatoId bigInt references contato
);

create table carrinho(
    id serial primary key,
	usuarioId bigInt references usuario,
    valorTotal numeric not null
);

create table itemCarrinho(
    id serial primary key,
    carrinhoId bigInt references carrinho,
    produtoId bigInt references produto,
	quantidade integer not null
);

#INSERTS
insert into carrinho (cidade, rua) values
('Porto Alegre', 'João Salomini 715/101'),
('Curitiba', 'João Coxa Branca 2155'),
('Chapecó', 'Furacão Verde 57'),
('São Paulo', 'Morumbi 1100'),
('Rio de Janeiro', 'Gal. Augusto Menezes 2500');

insert into produto(nome, preco) values
('Playstation 4', 4000),
('FIFA 17', 199.90),
('Battle Field 1', 225.90),
('Playstation 3', 1499.90),
('Playstation 2', 399.90),
('Playstation 1', 99.90);


insert into itemcarrinho(carrinhoid, produto, quantidade) values
(1,1,1),
(1,2,1),
(2,3,1),
(3,4,1),
(4,5,1),
(5,1,1),
(5,3,1);

#UPDATES
UPDATE itemcarrinho SET quantidade=2 WHERE carrinhoid=1 and produto=2;

#SELECTS       
select * from produto;
select * from carrinho;
select * from itemcarrinho;

select * from carrinho as c 
join itemCarrinho as ic 
on c.id = ic.carrinhoid 
join produto as p 
on ic.produtoId = p.id 
where c.id=1 order by c.id


SELECT c.id, c.cidade, c.rua, ic.quantidade, p.id, p.nome, p.preco FROM carrinho as c 
join itemcarrinho as ic on c.id= ic.carrinhoid 
join produto as p on p.id = ic.produto 
where c.id=1;

#ALTERS
--ALTERS
alter table carrinho alter column valortotal drop not null;