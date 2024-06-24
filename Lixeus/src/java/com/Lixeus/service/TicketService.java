/*
 * Autor: Elis Mattosinho
 * Descrição: Serviço para gerenciar operações CRUD para a entidade Ticket.
 */

package com.Lixeus.service;

import com.Lixeus.dao.TicketDAO;
import com.Lixeus.dao.TicketDAOImpl;
import com.Lixeus.model.Ticket;
import java.sql.SQLException;
import java.util.List;

public class TicketService {
    private final TicketDAO ticketDAO;

    public TicketService() throws SQLException {
        ticketDAO = new TicketDAOImpl();
    }

    public int insertTicket(Ticket ticket) {
       return ticketDAO.insertTicket(ticket);
    }

    public void updateTicket(Ticket ticket) {
        ticketDAO.updateTicket(ticket);
    }

    public void deleteTicket(int id) {
        ticketDAO.deleteTicket(id);
    }

    public Ticket getTicketById(int id) {
        return ticketDAO.getTicketById(id);
    }

    public List<Ticket> listTickets() {
        return ticketDAO.listTickets();
    }

    public List<Ticket> getTicketsByCompra(int idCompra) {
        return ticketDAO.getTicketsByCompra(idCompra);
    }

    public List<Ticket> getTicketsByIds(List<Integer> ids) {
        return ticketDAO.getTicketsByIds(ids);
    }
}
