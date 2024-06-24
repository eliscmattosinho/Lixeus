/*
 * Author: Elis Mattosinho
 * Descrição: Classe modelo que representa a entidade Compra.
 */

package com.Lixeus.model;

import com.Lixeus.util.Enums.StatusCompraEnum;
import java.time.LocalDate;
import java.util.List;

public class Compra {
    private int idCompra;
    private int idTicket;
    private int idCliente;
    private LocalDate dataCompra;
    private String formaPagamento;
    private int quantidadeTickets;
    private StatusCompraEnum statusCompra;
    private double total;

    // Getters e Setters

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getQuantidadeTickets() {
        return quantidadeTickets;
    }

    public void setQuantidadeTickets(int quantidadeTickets) {
        this.quantidadeTickets = quantidadeTickets;
    }

    public String  getStatusCompra() {
        return statusCompra != null ? statusCompra.name() : null;
    }

    public void setStatusCompra(String statusCompra) {
        try {
            this.statusCompra = StatusCompraEnum.valueOf(statusCompra.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status de compra inválido: " + statusCompra);
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private List<Atracao> atracoes;

    public List<Atracao> getAtracoes() {
        return atracoes;
    }

    public void setAtracoes(List<Atracao> atracoes) {
        this.atracoes = atracoes;
    }


    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", idTicket=" + idTicket +
                ", idCliente=" + idCliente +
                ", dataCompra=" + dataCompra +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", quantidadeTickets=" + quantidadeTickets +
                ", statusCompra='" + getStatusCompra() + '\'' +
                ", total=" + total +
                ", atracoes=" + atracoes +
                '}';
    }
}
