INSERT INTO public.endereco(
	id_endereco, bairro, n_cep, complemento, localidade, logradouro, numero, uf)
	VALUES (1, 'Vila Ema', '03282-001', 'Apto.220', 'São Paulo', 'Avenida Vila Ema', 5446, 'SP');

INSERT INTO public.endereco(
	id_endereco, bairro, n_cep, complemento, localidade, logradouro, numero, uf)
	VALUES (2, 'Cidade Continental', '03243-080', '273', 'São Paulo', 'Rua Vitória do Mearim', 273, 'SP');

INSERT INTO public.user_vendedor(
	id_vendedor, n_cnpj, email, nome, senha, telefone)
	VALUES (1, '1234567890', 'j.silva@email.com', 'José da Silva', '1234', '11900000000');

INSERT INTO public.loja(
	id_loja, descricao, nome, endereco_id_endereco, vendedor_id_vendedor)
	VALUES (1, 'Loja de Materiais de Construção', 'Seu Zé - Loja de Materiais de Construção', 2, 1);

INSERT INTO public.user_entregador(
	id_entregador, n_cnh, n_cpf, email, nome, senha, id_vendedor)
	VALUES (1, '2222222222', '22222222222', 'j.oliveira@email.com', 'João Oliveira', '1234', 1);

INSERT INTO public.user_entregador(
	id_entregador, n_cnh, n_cpf, email, nome, senha, id_vendedor)
	VALUES (2, '111111111', '111111111', 'm.oliveira@email.com', 'Mario Oliveira', '1234', 1);

INSERT INTO public.user_comprador(
	id_comprador, n_cpf, email, nome, senha, telefone, endereco_id_endereco)
	VALUES (1, '34857844898', 'r.brauna@email.com', 'Ryan Brauna', '12345', '11986743588', 1);

INSERT INTO public.pedidos(
	id_pedido, descricao, numero_pedido, preco, id_comprador, id_endereco, id_entregador, id_loja)
	VALUES (1, '1 metro de Areia', 001 , 17.50, 1, 1, 1, 1);