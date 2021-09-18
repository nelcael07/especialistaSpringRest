CREATE TABLE pedido (
	id BIGINT NOT NULL AUTO_INCREMENT,
	taxa_frete BIGINT NOT NULL,
	valorTotal BIGINT NOT NULL,
	data_criacao DATETIME NOT NULL,
	data_cancelamento DATETIME,
	data_entrega DATETIME, 
	endereco_cidade_id BIGINT NOT null,
	endereco_cep varchar(9) NOT null,
	endereco_logradouro varchar(100) NOT null,
	endereco_numero varchar(20) NOT null,
	endereco_complemento varchar(60) NOT null,
	endereco_bairro varchar(60) NOT null,
	status_pedido VARCHAR(12) NOT NULL,
	restaurante_id BIGINT NOT NULL,
	cliente_id BIGINT NOT NULL,
	forma_pagamento_id BIGINT NOT NULL,
	
	
	PRIMARY KEY (id)
);

CREATE TABLE item_pedido(
	id BIGINT NOT NULL AUTO_INCREMENT,
	quantidade BIGINT not NULL,
	preco_unitario NUMERIC(7,2) NOT null,
	preco_total NUMERIC(7,2) NOT null,
	observacao VARCHAR(100),
	pedido_id BIGINT NOT NULL,
	produto_id BIGINT NOT NULL,
	
	
	PRIMARY KEY (id)
);

ALTER TABLE pedido ADD CONSTRAINT fk_restaurante
FOREIGN KEY (restaurante_id) REFERENCES restaurante(id);

ALTER TABLE pedido ADD CONSTRAINT fk_cliente 
FOREIGN KEY (cliente_id) REFERENCES usuario(id);

ALTER TABLE pedido ADD CONSTRAINT fk_forma_pagamento
FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id);

ALTER TABLE item_pedido ADD CONSTRAINT fk_pedido
FOREIGN KEY (pedido_id) REFERENCES pedido(id);

ALTER TABLE item_pedido ADD CONSTRAINT fk_produto
FOREIGN KEY (produto_id) REFERENCES produto(id);
