
CREATE TABLE IF NOT EXISTS Cargos(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL
)

CREATE TABLE IF NOT EXISTS Permissao(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(30) NOT NULL
)

CREATE TABLE IF NOT EXISTS usuarios_cargos(
    id SERIAL PRIMARY KEY,
    usuario_id BIGINT REFERENCES Usuarios(id),
    cargo_id BIGINT REFERENCES Cargos(id)
)

CREATE TABLE IF NOT EXISTS cargos_permissao(
    id SERIAL PRIMARY KEY,
    cargo_id BIGINT REFERENCES Cargos(id),
    permissao_id BIGINT REFERENCES Permissao(id)
)