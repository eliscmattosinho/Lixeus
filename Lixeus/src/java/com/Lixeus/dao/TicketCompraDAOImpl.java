/*
 * Author: Elis Mattosinho
 * Descrição: Implementação da interface TickeCompraDAO para operações CRUD com a entidade Ticket.
 */

package com.Lixeus.dao;

import com.Lixeus.model.Ticket;
import com.Lixeus.model.TicketCompra;
import com.Lixeus.util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketCompraDAOImpl implements TicketCompraDAO {

    private final Connection connection;

    public TicketCompraDAOImpl() throws SQLException {
        connection = Conexao.estabeleceConexao();
    }

    // Método para adicionar tickets na compra
    @Override
    public void addTicketCompra(TicketCompra ticketCompra) throws SQLException {
        String sql = "INSERT INTO ticketsporcompra (ticket_id, compra_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, ticketCompra.getIdTicket());
            pstmt.setInt(2, ticketCompra.getIdCompra());

            pstmt.executeUpdate();
        }
    }

    // Método para pegar compras por id
    @Override
    public List<TicketCompra> getTicketComprasByCompraId(int compraId) throws SQLException {
        List<TicketCompra> ticketCompras = new ArrayList<>();
        String sql = "SELECT * FROM compras WHERE id_compra = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, compraId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TicketCompra ticketCompra = new TicketCompra();
                    Ticket ticket = new Ticket();
                    ticketCompra.setIdTicket(rs.getInt("ticket_id"));
                    ticketCompra.setIdCompra(rs.getInt("id_compra"));
                    ticket.setStatus(rs.getString("ticket_status"));
                    ticketCompras.add(ticketCompra);
                }
            }
        }
        return ticketCompras;
    }

    // Método para pegar as compras de um usuário
    @Override
    public List<TicketCompra> getTicketComprasByUserId(int cliente_id) throws SQLException {
        List<TicketCompra> ticketCompras = new ArrayList<>();
        String sql = "SELECT * FROM compras WHERE cliente_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cliente_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TicketCompra ticketCompra = new TicketCompra();
                    Ticket ticket = new Ticket();
                    ticketCompra.setIdTicketCompra(rs.getInt("id_compra"));
                    ticketCompra.setIdTicket(rs.getInt("ticket_id"));
                    ticketCompra.setIdCompra(rs.getInt("id_compra"));
                    ticket.setStatus(rs.getString("ticket_status"));
                    ticketCompras.add(ticketCompra);
                }
            }
        }
        return ticketCompras;
    }

    // Método para atualizar uma compra
    @Override
    public void updateTicketCompra(TicketCompra ticketCompra) throws SQLException {
        String sql = "UPDATE compras SET ticket_id = ?, id_compra = ? WHERE id_compra = ?";
        Ticket ticket = new Ticket();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, ticketCompra.getIdTicket());
            pstmt.setInt(2, ticketCompra.getIdCompra());
            pstmt.setString(3, ticket.getStatus());
            pstmt.setInt(4, ticketCompra.getIdTicketCompra());
            pstmt.executeUpdate();
        }
    }

    // Método para deletar uma compra
    @Override
    public void deleteTicketCompra(int id) throws SQLException {
        String sql = "DELETE FROM compras WHERE id_compra = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
