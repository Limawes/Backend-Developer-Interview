-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization

-- Criando user default do app (admin) apenas se não existir
INSERT INTO public.tbl_user (username, email, "password")
SELECT 'admin', 'admin@mobiauto.com', '$2a$10$jmrtALi9EcE1vA.zTRWJxepnMelmFlkOWWrnqwBUWyxLNBfnvlbsK'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_user WHERE username = 'admin');

-- Criando principais permissões apenas se não existirem
INSERT INTO public.tbl_system_roles (id, role_name)
SELECT 1, 'ROLE_ADMIN' WHERE NOT EXISTS (SELECT 1 FROM public.tbl_system_roles WHERE id = 1);

INSERT INTO public.tbl_system_roles (id, role_name)
SELECT 2, 'ROLE_PROPIETARIO' WHERE NOT EXISTS (SELECT 1 FROM public.tbl_system_roles WHERE id = 2);

INSERT INTO public.tbl_system_roles (id, role_name)
SELECT 3, 'ROLE_GERENTE' WHERE NOT EXISTS (SELECT 1 FROM public.tbl_system_roles WHERE id = 3);

INSERT INTO public.tbl_system_roles (id, role_name)
SELECT 4, 'ROLE_ASSISTENTE' WHERE NOT EXISTS (SELECT 1 FROM public.tbl_system_roles WHERE id = 4);

-- Inserindo a permissão default do app que será a de "administrador" apenas se não existir
INSERT INTO public.tbl_user_role (user_id, role_id)
SELECT 1, 1 WHERE NOT EXISTS (SELECT 1 FROM public.tbl_user_role WHERE user_id = 1 AND role_id = 1);


-- Clientes
INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'João Silva', 'joao@email.com', '11987654321'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'João Silva');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Ana Souza', 'ana@email.com', '11955556666'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Ana Souza');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Pedro Santos', 'pedro@email.com', '11933334444'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Pedro Santos');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Mariana Costa', 'mariana@email.com', '11999998888'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Mariana Costa');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Lucas Oliveira', 'lucas@email.com', '11977775555'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Lucas Oliveira');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Carolina Lima', 'carolina@email.com', '11922223333'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Carolina Lima');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Gustavo Pereira', 'gustavo@email.com', '11966667777'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Gustavo Pereira');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Camila Santos', 'camila@email.com', '11944445555'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Camila Santos');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Rafaela Silva', 'rafaela@email.com', '11988889999'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Rafaela Silva');

INSERT INTO public.tbl_clientes (nome, email, telefone)
SELECT 'Felipe Pereira', 'felipe@email.com', '11911112222'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_clientes WHERE nome = 'Felipe Pereira');

-- Veículos
INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Toyota', 'Rav4', 'Adventure', '2023'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Toyota' AND modelo = 'Rav4' AND versao = 'Adventure' AND ano_modelo = '2023');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Ford', 'Focus', 'SE', '2022'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Ford' AND modelo = 'Focus' AND versao = 'SE' AND ano_modelo = '2022');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Chevrolet', 'Tracker', 'Premier', '2024'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Chevrolet' AND modelo = 'Tracker' AND versao = 'Premier' AND ano_modelo = '2024');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Volkswagen', 'Tiguan', 'Highline', '2023'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Volkswagen' AND modelo = 'Tiguan' AND versao = 'Highline' AND ano_modelo = '2023');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Honda', 'HR-V', 'Touring', '2023'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Honda' AND modelo = 'HR-V' AND versao = 'Touring' AND ano_modelo = '2023');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Hyundai', 'Creta', 'Prestige', '2022'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Hyundai' AND modelo = 'Creta' AND versao = 'Prestige' AND ano_modelo = '2022');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Nissan', 'Kicks', 'SL', '2024'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Nissan' AND modelo = 'Kicks' AND versao = 'SL' AND ano_modelo = '2024');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Jeep', 'Compass', 'Longitude', '2023'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Jeep' AND modelo = 'Compass' AND versao = 'Longitude' AND ano_modelo = '2023');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'Renault', 'Duster', 'Iconic', '2023'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'Renault' AND modelo = 'Duster' AND versao = 'Iconic' AND ano_modelo = '2023');

INSERT INTO public.tbl_veiculos (marca, modelo, versao, ano_modelo)
SELECT 'BMW', 'X3', 'xDrive30i', '2022'
WHERE NOT EXISTS (SELECT 1 FROM public.tbl_veiculos WHERE marca = 'BMW' AND modelo = 'X3' AND versao = 'xDrive30i' AND ano_modelo = '2022');


