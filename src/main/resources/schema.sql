-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization
CREATE TABLE IF NOT EXISTS Revendas (
    id_revenda SERIAL PRIMARY KEY,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    nome_social VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    id_revenda INT REFERENCES Revendas(id_revenda)
);

CREATE TABLE IF NOT EXISTS Veiculos (
    id_veiculo SERIAL PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    versao VARCHAR(100) NOT NULL,
    ano_modelo VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Clientes (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Oportunidades (
    id_oportunidade SERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL DEFAULT 'novo',
    motivo_conclusao TEXT,
    id_cliente INT REFERENCES Clientes(id_cliente),
    id_veiculo INT REFERENCES Veiculos(id_veiculo),
    id_revenda INT REFERENCES Revendas(id_revenda),
    id_responsavel INT REFERENCES Usuarios(id_usuario),
    data_atribuicao TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --data e hora que uma oportunidade foi atribuida a um responsável
    data_conclusao TIMESTAMP -- data e hora que a oportunidade foi concluida
);

CREATE TABLE IF NOT EXISTS Cargos(
    id_cargo SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Permissao(
    id_permissao SERIAL PRIMARY KEY,
    nome VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios_cargos(
    id_usuarios_cargos SERIAL PRIMARY KEY,
    id_usuario BIGINT REFERENCES Usuarios(id_usuario),
    id_cargo BIGINT REFERENCES Cargos(id_cargo)
);

CREATE TABLE IF NOT EXISTS cargos_permissao(
    id_cargos_permissao SERIAL PRIMARY KEY,
    id_cargo BIGINT REFERENCES Cargos(id_cargo),
    id_permissao BIGINT REFERENCES Permissao(id_permissao)
);

--garantir somente valores válidos na tabela
ALTER TABLE Oportunidades DROP CONSTRAINT IF EXISTS chk_status;
ALTER TABLE Oportunidades
ADD CONSTRAINT chk_status
CHECK (status IN ('novo', 'em atendimento', 'concluido'));