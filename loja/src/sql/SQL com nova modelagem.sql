create table produto(
    id serial primary key,
    nome varchar(20) not null,
    preco numeric 
);

create table Endereco(
	id serial primary key,
	cidade varchar(50) not null,
	rua varchar(50) not null,
	numero integer not null,
	complemento varchar(30)
);

create table contato(
	id serial primary key,
	email VARCHAR(50)
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
    valorTotal numeric
);

create table itemCarrinho(
    id serial primary key,
    carrinhoId bigInt references carrinho,
    produtoId bigInt references produto,
	quantidade integer not null
);

#INSERTS
insert into endereco(cidade, rua, numero, complemento) values
('Porto Alegre', 'João Salomini', 715, '101'),
('Curitiba', 'João Coxa Branca', 2155, null),
('Chapecó', 'Furacão Verde', 57,'Beco do índio 20'),
('São Paulo', 'Morumbi', 1100, null),
('Florianópolis', 'Rio vermolho', 1715, null),
('Rio de Janeiro', 'Gal. Augusto Menezes', 2500, null);

insert into produto(nome, preco) values
('Playstation 4', 4000),
('FIFA 17', 199.90),
('Battle Field 1', 225.90),
('Playstation 3', 1499.90),
('Playstation 2', 399.90),
('Playstation 1', 99.90);

insert into contato(email) values
('kaksongracie@gmail.com'),
('coxaAteMorre@hotmail.com.br'),
('carolosZagueiro@terra.com.br'),
('pedroPedrada@outlook.com'),
('rickSilveira@gmail.com'),
('kayronSilveira@gmail.com');

insert into carrinho (usuarioId) values
(1),
(2),
(3),
(4),
(5),
(6);

insert into itemcarrinho(carrinhoid, produtoId, quantidade) values
(1,1,1),
(1,2,1),
(2,3,1),
(3,4,1),
(4,5,1),
(5,1,1),
(5,3,1),
(6,6,1);

insert into usuario(nome, sobrenome, documentoIdent, dataNasc, enderecoId, contatoId) values
('Thiago', 'Machado Silveira', '00578710008', TO_DATE('12-12-1984', 'DD-MM-YYYY'), 1),
('Thales', 'Albuquerquer Rodriguez', '00678711010', TO_DATE('10-09-1970', 'DD-MM-YYYY'), 2),
('Carlos', 'Nogueira Coloruva', '10057520014', TO_DATE('22-01-1990', 'DD-MM-YYYY'), 3),
('Pedro', 'Siria Bogotá', '00489535418', TO_DATE('22-01-1994', 'DD-MM-YYYY'), 4),
('Rickson', 'da Silva Silveira', '57894865480', TO_DATE('15-05-2010', 'DD-MM-YYYY'), 5),
('Kayron', 'da Silva Silveira', '59845630084', TO_DATE('18-09-2011', 'DD-MM-YYYY'), 6);

insert into telefone(contatoId, prefixotel, numero) values
(1, '051', '996717010'),
(1, '051', '98314277'),
(2, '041', '999574828'),
(3, '049', '985784922'),
(4, '011', '981486751'),
(5, '048', '982467711'),
(6, '021', '997364853');

#UPDATES
UPDATE itemcarrinho SET quantidade=2 WHERE carrinhoid=1 and produto=2;
update contato set email='kayronSilveira@gmail.com' where id = 6
update endereco set cidade='Florianópolis', rua='Rio vermolho', numero = 1715, complemento=null where id = 5

#SELECTS       
select * from produto;
select * from carrinho;
select * from itemcarrinho;
select * from contato;
select * from endereco;
select * from usuario;
select * from telefone;

####SELECTS DE MÉTODOS

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

select * from carrinho as c 
join usuario as u on c.usuarioid = u.id
join contato as con on u.contatoId = con.id
join endereco as ende on u.enderecoId = ende.id
join itemCarrinho as ic on c.id = ic.carrinhoid 
join produto as p on ic.produtoId = p.id 
where c.id=7 order by c.id

#ALTERS
--ALTERS
alter table endereco alter column complemento TYPE varchar(30);
alter table carrinho alter column valortotal drop not null;