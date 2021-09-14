insert into estado(nome) values ('Mato grosso')
insert into estado(nome) values ('São paulo')
insert into estado(nome) values ('Rio grande do sul')

insert into cidade(nome, estado_id) values ('Cuiabá', 1)
insert into cidade(nome, estado_id) values ('Jumdiai', 2)
insert into cidade(nome, estado_id) values ('Porto Alegre', 3)

insert into permissao(nome, descricao) values ('fazer tudo', 'faz o que quiser!')

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Italiana');


insert into restaurante (nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_numero, endereco_logradouro, endereco_complemento, endereco_bairro) values ('Papitos', '20', '2', utc_timestamp, utc_timestamp, '1', '78085100', '132', '100','casa', 'vista alegre');
insert into restaurante (nome,taxa_frete, cozinha_id,data_cadastro, data_atualizacao) values ('Carolina', 25, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Pampas', 35, 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento(descricao) values ('cartão de credito')
insert into forma_pagamento(descricao) values ('cartão de debito')

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (2,2), (3,1), (3,2);
