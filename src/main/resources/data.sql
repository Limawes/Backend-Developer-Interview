-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization

-- Cargos
INSERT INTO public.cargos (nome) VALUES('Proprietario');
INSERT INTO public.cargos (nome) VALUES('Gerente');
INSERT INTO public.cargos (nome) VALUES('Assistente');

-- Clientes
INSERT INTO public.clientes (nome, email, telefone)
VALUES
  ('João Silva', 'joao@email.com', '11987654321'),
  ('Ana Souza', 'ana@email.com', '11955556666'),
  ('Pedro Santos', 'pedro@email.com', '11933334444'),
  ('Mariana Costa', 'mariana@email.com', '11999998888'),
  ('Lucas Oliveira', 'lucas@email.com', '11977775555'),
  ('Carolina Lima', 'carolina@email.com', '11922223333'),
  ('Gustavo Pereira', 'gustavo@email.com', '11966667777'),
  ('Camila Santos', 'camila@email.com', '11944445555'),
  ('Rafaela Silva', 'rafaela@email.com', '11988889999'),
  ('Felipe Pereira', 'felipe@email.com', '11911112222');


-- Veículos
INSERT INTO public.veiculos (marca, modelo, versao, ano_modelo)
VALUES
  ('Toyota', 'Rav4', 'Adventure', '2023'),
  ('Ford', 'Focus', 'SE', '2022'),
  ('Chevrolet', 'Tracker', 'Premier', '2024'),
  ('Volkswagen', 'Tiguan', 'Highline', '2023'),
  ('Honda', 'HR-V', 'Touring', '2023'),
  ('Hyundai', 'Creta', 'Prestige', '2022'),
  ('Nissan', 'Kicks', 'SL', '2024'),
  ('Jeep', 'Compass', 'Longitude', '2023'),
  ('Renault', 'Duster', 'Iconic', '2023'),
  ('BMW', 'X3', 'xDrive30i', '2022');
