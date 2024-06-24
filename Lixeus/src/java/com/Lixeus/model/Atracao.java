/*
* Author: Matheus Maqueda
 Esse arquivo é o modelo da entidade 'atracoes' do banco de dados
*/

package com.Lixeus.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Atracao {

    private int id;
    private String titulo;
    private String descricao;
    private double preco_ticket;
    private LocalDate data;
    private LocalTime hora_ini;
    private LocalTime hora_fim;
    private int max_ticket;
    private int max_cliente_ticket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nome) {
        this.titulo = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco_ticket() {
        return preco_ticket;
    }

    public void setPreco_ticket(double preco_ticket) {
        this.preco_ticket = preco_ticket;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(LocalTime hora_ini) {
        this.hora_ini = hora_ini;
    }

    public LocalTime getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(LocalTime hora_fim) {
        this.hora_fim = hora_fim;
    }

    public int getMax_ticket() {
        return max_ticket;
    }

    public void setMax_ticket(int max_ticket) {
        this.max_ticket = max_ticket;
    }

    public int getMax_cliente_ticket() {
        return max_cliente_ticket;
    }

    public void setMax_cliente_ticket(int max_cliente_ticket) {
        this.max_cliente_ticket = max_cliente_ticket;
    }

    @Override
    public String toString() {
        return "Atracao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
