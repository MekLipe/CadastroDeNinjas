-- Criar a tabela de missões primeiro, porque `tb_cadastro` depende dela

CREATE TABLE TB_MISSOES (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
dificuldade VARCHAR(255) NOT NULL
);