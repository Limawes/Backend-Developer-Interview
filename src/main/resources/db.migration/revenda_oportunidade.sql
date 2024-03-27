
ALTER TABLE Oportunidades
ADD CONSTRAINT fk_oportunidades_revendas
FOREIGN KEY (loja_id)
REFERENCES Revendas(id_revenda);
