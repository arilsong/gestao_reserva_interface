create database fase2;

use fase2;

CREATE TABLE acomodacao (
    num_quarto INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    qtd_leitos INT NOT NULL,
    tamanho VARCHAR(30) not null,
    preco DECIMAL(10, 2) NOT NULL,
    descricao TEXT,
    estado TINYINT DEFAULT 0 NOT NULL
);


INSERT INTO acomodacao (num_quarto, nome, tipo, qtd_leitos, tamanho, preco, descricao, estado) VALUES
(101, 'Suíte Luxo', 'Suíte', 2, 'Grande', 350.00, 'Suíte de luxo com vista para o mar', 0),
(102, 'Quarto Standard', 'Standard', 1, 'Médio', 150.00, 'Quarto padrão com cama de casal', 1),
(103, 'Suíte Executiva', 'Suíte', 2, 'Grande', 400.00, 'Suíte executiva com área de trabalho', 0),
(104, 'Quarto Família', 'Standard', 4, 'Grande', 200.00, 'Quarto amplo para famílias', 2),
(105, 'Cobertura', 'Cobertura', 3, 'Grande', 800.00, 'Cobertura com jacuzzi e vista panorâmica', 0),
(106, 'Quarto Econômico', 'Econômico', 1, 'Pequeno', 100.00, 'Quarto simples para uma pessoa', 1),
(107, 'Suíte Nupcial', 'Suíte', 2, 'Grande', 500.00, 'Suíte especial para lua de mel', 0),
(108, 'Quarto Twin', 'Standard', 2, 'Médio', 160.00, 'Quarto com duas camas de solteiro', 0),
(109, 'Quarto Triplo', 'Standard', 3, 'Médio', 180.00, 'Quarto com três camas de solteiro', 1),
(110, 'Quarto Deluxe', 'Deluxe', 2, 'Grande', 250.00, 'Quarto deluxe com varanda', 0);


CREATE TABLE cliente (
    id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);


INSERT INTO cliente (nome, sobrenome, email, senha, telefone) VALUES
('Ana', 'Silva', 'ana.silva@example.com', 'senha123', '1234567890'),
('Bruno', 'Souza', 'bruno.souza@example.com', 'senha123', '0987654321'),
('Carla', 'Mendes', 'carla.mendes@example.com', 'senha123', '1122334455'),
('Diego', 'Ferreira', 'diego.ferreira@example.com', 'senha123', '5566778899'),
('Elisa', 'Costa', 'elisa.costa@example.com', 'senha123', '6677889900');


CREATE TABLE reserva (
    id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    data_checkin DATE NOT NULL,
    data_checkout DATE NOT NULL,
    valor_total DOUBLE NOT NULL,
    estado TINYINT DEFAULT 0 NOT NULL,
    cliente INT UNSIGNED NOT NULL,
    quarto INT unique,
    PRIMARY KEY (id),
    FOREIGN KEY (cliente) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (quarto) REFERENCES acomodacao(num_quarto) ON DELETE RESTRICT ON UPDATE CASCADE
);



