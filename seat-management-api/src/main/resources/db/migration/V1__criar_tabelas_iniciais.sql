-- Criação da tabela Poltrona
CREATE TABLE poltrona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(255) NOT NULL,
    disponivel BOOLEAN NOT NULL DEFAULT TRUE,
    pessoa VARCHAR(255),
    conteudo VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Criação da tabela HistoricoUsoPoltrona
CREATE TABLE historico_uso_poltrona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    poltrona_id BIGINT NOT NULL,
    pessoa VARCHAR(255),
    tipo_movimentacao VARCHAR(255) NOT NULL,
    data_alocacao TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Índice opcional para melhorar consultas no histórico por poltrona_id (não obrigatório, mas recomendado)
CREATE INDEX idx_historico_poltrona_id ON historico_uso_poltrona (poltrona_id);