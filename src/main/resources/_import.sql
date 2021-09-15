insert into estado(nome) values ('Mato grosso')
insert into estado(nome) values ('São paulo')
insert into estado(nome) values ('Rio grande do sul')

insert into cidade(nome, estado_id) values ('Cuiabá', 1)
insert into cidade(nome, estado_id) values ('Jumdiai', 2)
insert into cidade(nome, estado_id) values ('Porto Alegre', 3)

insert into permissao(nome, descricao) values ('fazer tudo', 'faz o que quiser!')
insert into permissao(nome, descricao) values ('fazer quase tudo', 'faz bastante coisa!')

insert into grupo(nome) values('grupo de gerentes')
insert into grupo(nome) values ('grupo de donos')

insert into usuario(nome, email, senha, data_cadastro) values ('nelcael', 'nelcaif@gmail.com', 'tate', utc_timestamp)
insert into usuario(nome, email, senha, data_cadastro) values ('ludmila', 'ludmilamelomoura@gmail.com', 'senhalud', utc_timestamp)
insert into usuario(nome, email, senha, data_cadastro) values ('magaraldo', 'magaraldo@gmail.com', 'senhamage', utc_timestamp)

insert into  usuario_grupo (usuario_id, grupo_id) values (1,2), (1,1), (2,1), (2,2), (3,2)

insert into grupo_permissao values(2, 1), (2,2), (1,2)

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Italiana');


insert into restaurante (nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_numero, endereco_logradouro, endereco_complemento, endereco_bairro) values ('Papitos', '20', '2', utc_timestamp, utc_timestamp, '1', '78085100', '132', '100','casa', 'vista alegre');
insert into restaurante (nome,taxa_frete, cozinha_id,data_cadastro, data_atualizacao) values ('Carolina', 25, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Pampas', 35, 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento(descricao) values ('cartão de credito')
insert into forma_pagamento(descricao) values ('cartão de debito')

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('marmita', 'sai na hora', 20, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('self service', 'pode comer a vontade', 33, true, 2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (2,2), (3,1), (3,2);
