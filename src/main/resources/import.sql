insert into estado(nome) values ('Mato grosso')
insert into estado(nome) values ('São paulo')
insert into estado(nome) values ('Rio grande do sul')

insert into cidade(nome, estado_id) values ('Cuiabá', 1)
insert into cidade(nome, estado_id) values ('Jumdiai', 2)
insert into cidade(nome, estado_id) values ('Porto Alegre', 3)

insert into permissao(nome, descricao) values ('fazer tudo', 'tem liberdade para fazer oque quiser')

insert into forma_pagamento(descricao) values ('cartão de credito')
insert into forma_pagamento(descricao) values ('cartão de debito')
insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Italiana');


insert into restaurante (nome,taxa_frete, cozinha_id) values ('Papitos', 20, 2);
insert into restaurante (nome,taxa_frete, cozinha_id) values ('Carolina', 25, 1);
