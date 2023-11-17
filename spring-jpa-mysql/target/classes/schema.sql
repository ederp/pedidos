CREATE TABLE `mydatabase`.`cliente` (
 `codCliente` INT NOT NULL,
 `nomeCliente` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`codCliente`));

ALTER TABLE `mydatabase`.`cliente`
CHANGE COLUMN `codCliente` `codCliente` INT(11) NOT NULL AUTO_INCREMENT,
ADD UNIQUE INDEX `id_UNIQUE` (`codCliente` ASC);


CREATE TABLE `mydatabase`.`pedido` (
 `numControlePedido` INT NOT NULL,
 `dataCadastro` DATE NOT NULL,
 `nomeProduto` VARCHAR(255) NOT NULL,
 `valorUnitario` DECIMAL(10, 2) NOT NULL,
 `qtdeProduto` INT NOT NULL,
 `valorTotal` DECIMAL(10, 2) NOT NULL,
 `codCliente` INT,
 PRIMARY KEY (`numControlePedido`),
 FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`));