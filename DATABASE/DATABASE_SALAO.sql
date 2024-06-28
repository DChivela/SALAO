-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25-Jun-2024 às 22:17
-- Versão do servidor: 10.4.32-MariaDB
-- versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `salaot`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `clienteID` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `morada` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`clienteID`, `nome`, `morada`, `telefone`, `email`) VALUES
(1, 'Domingos Chivela', 'Bairro da Mitcha', '(244)947-449-744', 'domingoscchivela@gmail.com'),
(5, 'Teste 2', 'Bairro Lucrêcia', '(   )   -   -   ', ''),
(6, 'Fernado Pessoa', 'Bairro Lucrêcia', '(244)921-002-002', 'fernandopes@gmail.com'),
(9, 'Teresa Gonsalves', 'Bairro Lucrêcia', '(244)945-839-450', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

CREATE TABLE `funcionarios` (
  `funcionarioID` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `morada` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `Utilizador` varchar(50) NOT NULL,
  `Senha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`funcionarioID`, `nome`, `cargo`, `morada`, `telefone`, `Utilizador`, `Senha`) VALUES
(1, 'Lucrêcia Fulano', 'Balconista', 'Comercial', '(244)923-001-001', 'l.fulano', '123'),
(4, 'Domingos Chivela', 'Gerente', 'Lubango', '(244)947-449-844', 'Admin', '123'),
(6, 'Albertina Satipamba', 'Gerente', 'Bairro Santo António', '(244)900-600-000', 'a.satipamba', '2023'),
(7, 'Teresa Gonsalves', 'Directora Geral', 'Bairro do Tchioco', '(244)900-600-000', 't.gonsalves', '000');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itensvenda`
--

CREATE TABLE `itensvenda` (
  `itemVendaID` int(11) NOT NULL,
  `vendaID` int(11) NOT NULL,
  `ServicoID` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `itensvenda`
--

INSERT INTO `itensvenda` (`itemVendaID`, `vendaID`, `ServicoID`, `quantidade`, `subtotal`) VALUES
(1, 7, 10, 1, 21530.00),
(2, 8, 6, 1, 500.00),
(3, 8, 8, 3, 3000.00),
(4, 9, 6, 2, 4500.00),
(5, 10, 6, 1, 2250.00),
(6, 11, 6, 1, 500.00),
(7, 11, 9, 2, 1000.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico`
--

CREATE TABLE `servico` (
  `ServicoID` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `preco` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `servico`
--

INSERT INTO `servico` (`ServicoID`, `descricao`, `preco`) VALUES
(6, 'Barba', 500.00),
(8, 'Coloração de cabelo', 2000.00),
(9, 'Manicure', 500.00),
(10, 'Corte de cabelo', 1000.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

CREATE TABLE `vendas` (
  `vendaID` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `valorTotal` decimal(10,2) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  `funcionarioID` int(11) DEFAULT NULL,
  `observacoes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`vendaID`, `data`, `valorTotal`, `clienteID`, `funcionarioID`, `observacoes`) VALUES
(2, '2024-06-20 11:48:09', 21530.00, 1, NULL, 'Primeira venda no sistema.'),
(3, '2024-06-20 11:56:23', 21530.00, 1, NULL, 'Segunda venda'),
(4, '2024-06-20 11:57:44', 21530.00, 5, NULL, 'Terceira venda'),
(5, '2024-06-20 11:58:47', 0.00, 5, NULL, ''),
(6, '2024-06-20 11:59:44', 21530.00, 5, NULL, '5º Venda registada no sistema'),
(7, '2024-06-20 12:02:39', 21530.00, 1, NULL, '5ª venda rgistada.'),
(8, '2024-06-20 12:26:57', 3500.00, 1, NULL, 'Venda a partir da área de trabalho'),
(9, '2024-06-20 12:45:32', 4500.00, 1, NULL, 'Segunda venda registada no sistema\ncom tudo já funcional'),
(10, '2024-06-20 15:51:36', 2250.00, 1, NULL, ''),
(11, '2024-06-25 20:59:22', 1500.00, 6, NULL, 'Teste da primeira venda');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`clienteID`);

--
-- Índices para tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`funcionarioID`);

--
-- Índices para tabela `itensvenda`
--
ALTER TABLE `itensvenda`
  ADD PRIMARY KEY (`itemVendaID`),
  ADD KEY `venda_id` (`vendaID`),
  ADD KEY `produto_id` (`ServicoID`);

--
-- Índices para tabela `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`ServicoID`);

--
-- Índices para tabela `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`vendaID`),
  ADD KEY `cliente_id` (`clienteID`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `clienteID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `funcionarioID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `itensvenda`
--
ALTER TABLE `itensvenda`
  MODIFY `itemVendaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `servico`
--
ALTER TABLE `servico`
  MODIFY `ServicoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `vendas`
--
ALTER TABLE `vendas`
  MODIFY `vendaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `itensvenda`
--
ALTER TABLE `itensvenda`
  ADD CONSTRAINT `itensvenda_ibfk_1` FOREIGN KEY (`vendaID`) REFERENCES `vendas` (`vendaID`),
  ADD CONSTRAINT `itensvenda_ibfk_2` FOREIGN KEY (`ServicoID`) REFERENCES `servico` (`ServicoID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
