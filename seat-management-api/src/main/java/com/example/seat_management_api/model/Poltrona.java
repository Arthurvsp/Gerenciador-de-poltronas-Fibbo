package com.example.seat_management_api.model;

import jakarta.persistence.*;

@Entity
public class Poltrona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Usando geração automática do ID
    private Long id;
    private String numero;
    private boolean disponivel;  // indica se a poltrona está disponível ou ocupada
    private String pessoa;  // quem alocou a poltrona

    @Column(name = "conteudo") // Supondo que o nome da coluna seja "conteudo"
    private String conteudo;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    // Construtores
    public Poltrona() {}

    public Poltrona(Long id, String numero, boolean disponivel) {
        this.id = id;
        this.numero = numero;
        this.disponivel = disponivel;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }
}