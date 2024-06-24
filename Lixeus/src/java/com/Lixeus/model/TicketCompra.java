/*
 * Author: Elis Mattosinho
 * Descrição: Classe modelo que representa a entidade TicketCompra.
 */

package com.Lixeus.model;

import com.Lixeus.util.Enums.StatusCompraEnum;

public class TicketCompra {
    private int idTicketCompra;
    private int idTicket;
    private int idCompra;
    private Ticket ticket;
    private StatusCompraEnum status;

    // Getters e Setters

    public int getIdTicketCompra() {
        return idTicketCompra;
    }

    public void setIdTicketCompra(int idTicketCompra) {
        this.idTicketCompra = idTicketCompra;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public StatusCompraEnum getStatus() {
        return status;
    }

    public void setStatus(StatusCompraEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TicketCompra{" +
                "idTicketCompra=" + idTicketCompra +
                ", idTicket=" + idTicket +
                ", idCompra=" + idCompra +
                ", status=" + status +
                '}';
    }
}
