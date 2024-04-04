-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization
CREATE TABLE IF NOT EXISTS public.tbl_revenda (
    id_revenda SERIAL PRIMARY KEY,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    nome_social VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tbl_veiculos (
    id_veiculo SERIAL PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    versao VARCHAR(100) NOT NULL,
    ano_modelo VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tbl_clientes (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

-- ################## Security ####################
CREATE TABLE IF NOT EXISTS public.tbl_user (
    id_user int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    username varchar NOT NULL,
    email varchar NOT NULL,
    "password" varchar NOT NULL,
    id_revenda INT REFERENCES tbl_revenda(id_revenda),
    CONSTRAINT tbl_user_pk PRIMARY KEY (id_user),
    CONSTRAINT tbl_user_un UNIQUE (email, username)
);

CREATE TABLE IF NOT EXISTS public.tbl_system_roles (
    id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    role_name varchar NOT NULL,
    CONSTRAINT tbl_system_roles_pk PRIMARY KEY (id),
    CONSTRAINT tbl_system_roles_un UNIQUE (role_name)
);

CREATE TABLE IF NOT EXISTS public.tbl_user_role (
    id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    user_id int8 NOT NULL,
    role_id int8 NOT NULL,
    CONSTRAINT tbl_user_role_pk PRIMARY KEY (id),
    CONSTRAINT tbl_user_role_fk FOREIGN KEY (role_id) REFERENCES public.tbl_system_roles(id),
    CONSTRAINT tbl_user_role_fk_1 FOREIGN KEY (user_id) REFERENCES public.tbl_user(id_user)
);
-- ################## // ####################

CREATE TABLE IF NOT EXISTS public.tbl_oportunidades (
    id_oportunidade SERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL DEFAULT 'novo',
    motivo_conclusao TEXT,
    id_cliente INT REFERENCES tbl_clientes(id_cliente),
    id_veiculo INT REFERENCES tbl_veiculos(id_veiculo),
    id_revenda INT REFERENCES tbl_revenda(id_revenda),
    id_responsavel INT REFERENCES tbl_user(id_user),
    data_atribuicao TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --data e hora que uma oportunidade foi atribuida a um responsável
    data_conclusao TIMESTAMP -- data e hora que a oportunidade foi concluida
);

--garantir somente valores válidos na tabela
ALTER TABLE public.tbl_oportunidades DROP CONSTRAINT IF EXISTS chk_status;
ALTER TABLE public.tbl_oportunidades
ADD CONSTRAINT chk_status
CHECK (status IN ('novo', 'em atendimento', 'concluido'));