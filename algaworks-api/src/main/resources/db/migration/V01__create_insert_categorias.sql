CREATE TABLE IF NOT EXISTS `curso`.`categoria`(
  `codigo` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Categories
-- -----------------------------------------------------
INSERT INTO categoria(nome) VALUES ('Lazer');
INSERT INTO categoria(nome) VALUES ('Alimentaçao');
INSERT INTO categoria(nome) VALUES ('Supermercado');
INSERT INTO categoria(nome) VALUES ('Farmacia');
INSERT INTO categoria(nome) VALUES ('Outros');