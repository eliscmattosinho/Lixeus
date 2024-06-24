/*
 * Author: Elis Mattosinho
 * Descrição: Classe modelo que representa a entidade Ticket.
 */

package com.Lixeus.model;

import com.Lixeus.util.CodigoAcessoGenerator;
import com.Lixeus.util.Enums.TicketStatusEnum;

public class Ticket {
    private int idTicket;
    private int idAtracao;
    private TicketStatusEnum status;
    private int idCompra;
    private String accessCode;

    // Getters e Setters

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdAtracao() {
        return idAtracao;
    }

    public void setIdAtracao(int idAtracao) {
        this.idAtracao = idAtracao;
    }

    public String getStatus() {
        return status != null ? status.name() : null;
    }

    public void setStatus(String status) {
        try {
            this.status = TicketStatusEnum.valueOf(status.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status de ticket inválido: " + status);
        }
    }
    
    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getAccessCode() {
        return accessCode ;
    }

    public void setAccessCode(String accessCode ) {
        this.accessCode  = accessCode ;
    }

    public void generateAcessCode() {
        CodigoAcessoGenerator generator = new CodigoAcessoGenerator();
        this.accessCode  = generator.gerarCodigoAcesso();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", idAtracao=" + idAtracao +
                ", status='" + status + '\'' +
                "idCompra=" + idCompra +
                "accessCode =" + accessCode  +
                '}';
    }
}
