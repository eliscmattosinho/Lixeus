/*
 * Author: Elis Mattosinho
 * Descrição: Interface de DAO para operações CRUD com a entidade Ticket.
 */

package com.Lixeus.dao;

import com.Lixeus.model.Ticket;
import java.util.List;

public interface TicketDAO {

    // MÉTODOS DE ALTERAÇÃO DE DADOS
    int insertTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void deleteTicket(int id);

    // MÉTODOS DE BUSCA DE TICKET(S)
    Ticket getTicketById(int id);
    List<Ticket> listTickets();
    List<Ticket> getTicketsByCompra(int idCompra);
    List<Ticket> getTicketsByIds(List<Integer> ids);
}
