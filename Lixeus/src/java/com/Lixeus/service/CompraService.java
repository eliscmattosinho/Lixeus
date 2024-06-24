/*
 * Autor: Elis Mattosinho
 * Descrição: Serviço para gerenciar operações CRUD para a entidade Compra.
 */

package com.Lixeus.service;

import com.Lixeus.dao.AtracaoDAO;
import com.Lixeus.dao.AtracaoDAOImpl;
import com.Lixeus.dao.CompraDAO;
import com.Lixeus.dao.CompraDAOImpl;
import com.Lixeus.dao.TicketCompraDAO;
import com.Lixeus.dao.TicketCompraDAOImpl;
import com.Lixeus.model.Atracao;
import com.Lixeus.model.Compra;
import com.Lixeus.model.TicketCompra;
import java.sql.SQLException;
import java.util.List;

public class CompraService {
    private final CompraDAO compraDAO;
    private final TicketCompraDAO ticketCompraDAO;
    private final AtracaoDAO atracaoDAO;

    public CompraService() throws SQLException {
        this.compraDAO = new CompraDAOImpl();
        this.ticketCompraDAO = new TicketCompraDAOImpl();
        this.atracaoDAO = new AtracaoDAOImpl();
    }

    public int createCompra(Compra compra) throws SQLException {
        return compraDAO.createCompra(compra);
    }

    public Compra getCompraById(int compraId) throws SQLException {
        return compraDAO.getCompraById(compraId);
    }

    public List<TicketCompra> getTicketsByCompraId(int compraId) throws SQLException {
        return ticketCompraDAO.getTicketComprasByCompraId(compraId);
    }

    public void updateCompra(Compra compra) throws SQLException {
        compraDAO.updateCompra(compra);
    }

    public void deleteCompra(int idCompra) throws SQLException {
        // Antes de deletar a compra, deletar os tickets associados a ela
        List<TicketCompra> ticketCompras = ticketCompraDAO.getTicketComprasByCompraId(idCompra);
        for (TicketCompra ticketCompra : ticketCompras) {
            ticketCompraDAO.deleteTicketCompra(ticketCompra.getIdTicketCompra());
        }

        // Agora deletar a compra
        compraDAO.deleteCompra(idCompra);
    }

    public List<Compra> getComprasByUserId(int userId) throws SQLException {
        return compraDAO.getComprasByUserId(userId);
    }

    public List<Atracao> getAtracoesByCompraId(int compraId) throws SQLException {
        return atracaoDAO.getAtracoesByCompraId(compraId);
    }

    public List<Compra> getAllCompras() throws SQLException {
        return compraDAO.getAllCompras();
    }
}
