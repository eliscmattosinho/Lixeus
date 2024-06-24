/*
 * Author: Elis Mattosinho
 * Descrição: Implementação da interface TicketDAO para operações CRUD com a entidade Ticket.
 */

package com.Lixeus.dao;

import com.Lixeus.model.Ticket;
import com.Lixeus.service.AtracaoService;
import com.Lixeus.util.CodigoAcessoGenerator;
import com.Lixeus.util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TicketDAOImpl implements TicketDAO {
    private final Connection connection;

    public TicketDAOImpl() throws SQLException {
        connection = Conexao.estabeleceConexao();
    }

    @Override
    public int insertTicket(Ticket ticket) {
        int generatedId = 0;

        CodigoAcessoGenerator generator = new CodigoAcessoGenerator();
        ticket.setAccessCode(generator.gerarCodigoAcesso());

        String sql = "INSERT INTO tickets (atracao_id, ticket_status, compra_id, codigo_acesso) VALUES (?, ?, ?, ?) RETURNING id_ticket";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ticket.getIdAtracao());
            ps.setObject(2, ticket.getStatus(), Types.OTHER);
            ps.setInt(3, ticket.getIdCompra());
            ps.setString(4, ticket.getAccessCode());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                generatedId = rs.getInt(1);

                String sql2 = "UPDATE atracoes SET max_ticket = ? WHERE id_atracao = ?";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                AtracaoService atracaoService = new AtracaoService();
                ps2.setInt(1, atracaoService.getAtracaoById(ticket.getIdAtracao()).getMax_ticket() - 1);
                ps2.setInt(2, ticket.getIdAtracao());
                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir ticket: " + e);
        }

        return generatedId;
    }

    public void generateAcessCode(Ticket ticket) {
        CodigoAcessoGenerator generator = new CodigoAcessoGenerator();
        ticket.setAccessCode(generator.gerarCodigoAcesso());
    }

    // Criação de tickets extras
    @Override
    public void updateTicket(Ticket ticket) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE tickets SET atracao_id=?, ticket_status=? WHERE id_ticket=?");
            ps.setInt(1, ticket.getIdAtracao());
            ps.setString(2, ticket.getStatus());
            ps.setInt(3, ticket.getIdTicket());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar: " + e);
        }
    }

    @Override
    public void deleteTicket(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tickets WHERE id_ticket=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e);
        }
    }

    @Override
    public Ticket getTicketById(int id) {
        Ticket ticket = new Ticket();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tickets WHERE id_ticket=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ticket.setIdTicket(rs.getInt("id_ticket"));
                ticket.setIdAtracao(rs.getInt("atracao_id"));
                ticket.setStatus(rs.getString("ticket_status"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar por Id: " + e);
        }
        return ticket;
    }

    // Pegar tickets por id
    @Override
    public List<Ticket> getTicketsByIds(List<Integer> ids) {
        List<Ticket> tickets = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM tickets WHERE id_ticket IN (");
            for (int i = 0; i < ids.size(); i++) {
                if (i > 0) {
                    query.append(", ");
                }
                query.append("?");
            }
            query.append(")");

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
            for (int i = 0; i < ids.size(); i++) {
                preparedStatement.setInt(i + 1, ids.get(i));
            }

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("id_ticket"));
                ticket.setIdAtracao(rs.getInt("atracao_id"));
                ticket.setStatus(rs.getString("ticket_status"));
                ticket.setAccessCode(rs.getString("codigo_acesso"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar tickets por IDs: " + e);
        }
        return tickets;
    }


    @Override
public List<Ticket> getTicketsByCompra(int idCompra) {
    List<Ticket> tickets = new ArrayList<>();
    try {
        String sql = "SELECT t.id_ticket, t.atracao_id, t.ticket_status, t.codigo_acesso " +
                     "FROM public.tickets t " +
                     "JOIN public.ticketsporcompra tc ON t.id_ticket = tc.ticket_id " +
                     "WHERE tc.compra_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idCompra);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Ticket ticket = new Ticket();
            ticket.setIdTicket(rs.getInt("id_ticket"));
            ticket.setIdAtracao(rs.getInt("atracao_id"));
            ticket.setStatus(rs.getString("ticket_status"));
            ticket.setAccessCode(rs.getString("codigo_acesso"));
            tickets.add(ticket);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao Buscar Tickets por id da compra: " + e);
    }

    return tickets;
}


    @Override
    public List<Ticket> listTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tickets ORDER BY id_ticket");
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("id_ticket"));
                ticket.setIdAtracao(rs.getInt("atracao_id"));
                ticket.setStatus(rs.getString("ticket_status"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Listar: " + e);
        }
        return tickets;
    }
}
