-- tabela Revendas
CREATE TABLE Revendas (
    id SERIAL PRIMARY KEY,
    codigo_identificador VARCHAR(50) UNIQUE NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    nome_social VARCHAR(100) NOT NULL
);

-- tabela Usuários
CREATE TABLE Usuarios (
    id SERIAL PRIMARY KEY,
    codigo_identificador VARCHAR(50) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    loja_id INT REFERENCES Revendas(id)
);

-- tabela Oportunidades
CREATE TABLE Oportunidades (
    id SERIAL PRIMARY KEY,
    codigo_identificador VARCHAR(50) UNIQUE NOT NULL,
    status VARCHAR(20) NOT NULL,
    motivo_conclusao TEXT,
    cliente_nome VARCHAR(100) NOT NULL,
    cliente_email VARCHAR(100) NOT NULL,
    cliente_telefone VARCHAR(20) NOT NULL,
    veiculo_marca VARCHAR(100) NOT NULL,
    veiculo_modelo VARCHAR(100) NOT NULL,
    veiculo_versao VARCHAR(100) NOT NULL,
    veiculo_ano_modelo INT NOT NULL,
    loja_id INT REFERENCES Revendas(id),
    responsavel_id INT REFERENCES Usuarios(id),
    data_atribuicao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_conclusao TIMESTAMP
);

ALTER TABLE Usuarios
ADD CONSTRAINT fk_loja_id
FOREIGN KEY (loja_id)
REFERENCES Revendas(id);
