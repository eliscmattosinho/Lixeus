/*
 * Autor: Elis Mattosinho
 * Descrição: Serviço para gerenciar operações CRUD para a entidade TicketCompra.
 */

package com.Lixeus.service;

import com.Lixeus.dao.TicketCompraDAO;
import com.Lixeus.dao.TicketCompraDAOImpl;
import com.Lixeus.model.TicketCompra;
import java.sql.SQLException;
import java.util.List;

public class TicketCompraService {
    private final TicketCompraDAO ticketCompraDAO;

    public TicketCompraService() throws SQLException {
        ticketCompraDAO = new TicketCompraDAOImpl();
    }

    public void addTicketCompra(TicketCompra ticketCompra) throws SQLException {
        ticketCompraDAO.addTicketCompra(ticketCompra);
    }

    public List<TicketCompra> getTicketComprasByCompraId(int compraId) throws SQLException {
        return ticketCompraDAO.getTicketComprasByCompraId(compraId);
    }

    public List<TicketCompra> getTicketComprasByUserId(int cliente_id) throws SQLException {
        return ticketCompraDAO.getTicketComprasByUserId(cliente_id);
    }

    public void updateTicketCompra(TicketCompra ticketCompra) throws SQLException {
        ticketCompraDAO.updateTicketCompra(ticketCompra);
    }

    public void deleteTicketCompra(int idTicketCompra) throws SQLException {
        ticketCompraDAO.deleteTicketCompra(idTicketCompra);
    }
}
