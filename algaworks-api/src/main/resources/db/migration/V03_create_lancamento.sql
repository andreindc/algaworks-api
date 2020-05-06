-- -----------------------------------------------------
-- Crete table lancamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `curso`.`lancamento`(
  `codigo` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  `decricao` VARCHAR(50) NOT NULL,
  `data_vencimiento` DATE NOT NULL,
  `data_pagamento` DATE,
  `valor` DECIMAL(10, 2) NOT NULL,
  `observacao` VARCHAR(100),
  `tipo` VARCHAR(20) NOT NULL,
  `codigo_categoria` BIGINT(20) NOT NULL,
  `codigo_pessoa` BIGINT(20) NOT NULL
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE curso.lancamento
ADD CONSTRAINT cnt_lanca_categ_fk FOREIGN KEY (codigo_categoria)
 REFERENCES curso.categoria (codigo);

ALTER TABLE curso.lancamento
ADD CONSTRAINT cnt_lanca_pesso_fk FOREIGN KEY (codigo_pessoa)
 REFERENCES curso.pessoa (codigo);

 INSERT INTO `curso`.`lancamento` (`decricao`, `data_vencimiento`, `valor`, `observacao`, `tipo`, `codigo_categoria`, `codigo_pessoa`)
 VALUES ('Salário mensal', '2020-04-10', 20000, 'Distribuçao de lucros', 'RECITA', 1, 1);

INSERT INTO `curso`.`lancamento` (`decricao`, `data_vencimiento`, `valor`, `observacao`, `tipo`, `codigo_categoria`, `codigo_pessoa`)
 VALUES ('Bahamas', '2020-04-11', 100.32, null, 'RECITA', 2, 2);

INSERT INTO `curso`.`lancamento` (`decricao`, `data_vencimiento`, `valor`, `observacao`, `tipo`, `codigo_categoria`, `codigo_pessoa`)
 VALUES ('Top Club', '2020-04-12', 203.6, null, 'RECITA', 3, 3);

INSERT INTO `curso`.`lancamento` (`decricao`, `data_vencimiento`, `valor`, `observacao`, `tipo`, `codigo_categoria`, `codigo_pessoa`)
 VALUES ('CEMIG', '2020-04-12', 346.22, 'Generaçao', 'RECITA', 3, 4);
