
CREATE TABLE Revendas (
    id SERIAL PRIMARY KEY,
    codigo_identificador VARCHAR(50) UNIQUE NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    nome_social VARCHAR(100) NOT NULL,
    ativo BOOLEAN DEFAULT true
);

CREATE TABLE Usuarios (
    id SERIAL PRIMARY KEY,
    codigo_identificador VARCHAR(50) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    loja_id INT REFERENCES Revendas(id)
);

CREATE TABLE Veiculos (
    id SERIAL PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    versao VARCHAR(100) NOT NULL,
    ano_modelo VARCHAR(100) NOT NULL
);

CREATE TABLE Clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE Oportunidades (
    id SERIAL PRIMARY KEY,
    codigo_identificador VARCHAR(50) UNIQUE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'novo',
    motivo_conclusao TEXT,
    cliente_id INT REFERENCES Clientes(id),
    veiculo_id INT REFERENCES Veiculos(id),
    loja_id INT REFERENCES Revendas(id),
    responsavel_id INT REFERENCES Usuarios(id),
    data_atribuicao TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --data e hora que uma oportunidade foi atribuida a um responsável
    data_conclusao TIMESTAMP -- data e hora que a oportunidade foi concluida
);

ALTER TABLE Usuarios
ADD CONSTRAINT fk_loja_id
FOREIGN KEY (loja_id)
REFERENCES Revendas(id);

--garantir somente valores válidos na tabela
ALTER TABLE Oportunidades
ADD CONSTRAINT chk_status
CHECK (status IN ('novo', 'em atendimento', 'concluido'));
