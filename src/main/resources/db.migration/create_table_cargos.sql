
CREATE TABLE IF NOT EXISTS Cargos(
    id_cargo SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL
)

CREATE TABLE IF NOT EXISTS Permissao(
    id_permissao SERIAL PRIMARY KEY,
    nome VARCHAR(30) NOT NULL
)

CREATE TABLE IF NOT EXISTS usuarios_cargos(
    id_usuarios_cargos SERIAL PRIMARY KEY,
    usuario_id BIGINT REFERENCES Usuarios(id_usuario),
    cargo_id BIGINT REFERENCES Cargos(id_cargo)
)

CREATE TABLE IF NOT EXISTS cargos_permissao(
    id_cargos_permissao SERIAL PRIMARY KEY,
    cargo_id BIGINT REFERENCES Cargos(id_cargo),
    permissao_id BIGINT REFERENCES Permissao(id)
)