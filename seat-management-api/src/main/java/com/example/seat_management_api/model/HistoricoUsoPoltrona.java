package com.example.seat_management_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class HistoricoUsoPoltrona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long poltronaId;
    private String pessoa;
    private String tipoMovimentacao; // Tipo da movimentação: "Ocupada" ou "Liberada"
    private LocalDateTime dataAlocacao; // Data da movimentação

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoltronaId() {
        return poltronaId;
    }

    public void setPoltronaId(Long poltronaId) {
        this.poltronaId = poltronaId;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public LocalDateTime getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(LocalDateTime dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }
}
