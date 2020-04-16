-- -----------------------------------------------------
-- Crete table Pessoa and Endereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `curso`.`pessoa`(
  `codigo` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `ativo` boolean NOT NULL,
  `logradouro` VARCHAR(20),
  `numero` VARCHAR(20),
  `complemento` VARCHAR(20),
  `barrio` VARCHAR(20),
  `cep` VARCHAR(20),
  `cidade` VARCHAR(20),
  `estado` VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO curso.pessoa(nome, ativo, logradouro, numero, complemento, barrio, cep, cidade, estado)
 VALUES ('Andreina', true, 'Vila','185','B','Afogados','550770','Recife','Pernanbuco');