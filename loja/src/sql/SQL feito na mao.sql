create table produto(
    id serial primary key,
    nome varchar(20) not null,
    quantidade integer not null,
    preco numeric not null
);

create table carrinho(
    id serial primary key,
    cidade varchar(30) not null,
    rua varchar(50) not null
);

create table itemCarrinho(
    id serial primary key,
    carrinhoId integer references carrinho,
    produto integer references produto
);
       