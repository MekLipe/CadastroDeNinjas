-- V2: Migrations para adicionar a coluna de RANK na tabela de cadastros

ALTER TABLE TB_CADASTRO
ADD COLUMN rank VARCHAR(255);