CREATE TABLE IF NOT EXISTS Revendas (
    id_revenda SERIAL PRIMARY KEY,
    codigo_identificador VARCHAR(50) UNIQUE NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    nome_social VARCHAR(100) NOT NULL,
    ativo BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS Usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    loja_id INT REFERENCES Revendas(id)
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
    cliente_id INT REFERENCES Clientes(id),
    veiculo_id INT REFERENCES Veiculos(id),
    loja_id INT REFERENCES Revendas(id),
    responsavel_id INT REFERENCES Usuarios(id),
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
    usuario_id BIGINT REFERENCES Usuarios(id_usuario),
    cargo_id BIGINT REFERENCES Cargos(id_cargo)
);

CREATE TABLE IF NOT EXISTS cargos_permissao(
    id_cargos_permissao SERIAL PRIMARY KEY,
    cargo_id BIGINT REFERENCES Cargos(id_cargo),
    permissao_id BIGINT REFERENCES Permissao(id)
);

ALTER TABLE Usuarios
ADD CONSTRAINT fk_loja_id
FOREIGN KEY (loja_id)
REFERENCES Revendas(id);

--garantir somente valores válidos na tabela
ALTER TABLE Oportunidades
ADD CONSTRAINT chk_status
CHECK (status IN ('novo', 'em atendimento', 'concluido'));


ALTER TABLE Oportunidades
ADD CONSTRAINT fk_oportunidades_revendas
FOREIGN KEY (loja_id)
REFERENCES Revendas(id_revenda);
