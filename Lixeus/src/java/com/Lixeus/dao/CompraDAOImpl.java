/*
 * Author: Elis Mattosinho
 * Descrição: Implementação da interface CompraDAO para operações CRUD com a entidade Compra.
 */

package com.Lixeus.dao;

import com.Lixeus.model.Compra;
import com.Lixeus.util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImpl implements CompraDAO {
    private final Connection connection;

    public CompraDAOImpl() throws SQLException {
        connection = Conexao.estabeleceConexao();
    }

    // Author: Matheus Maqueda
    @Override
    public int createCompra(Compra compra) {
        int generatedId = 0;

        try {
            PreparedStatement pgs = connection.prepareStatement(
                "INSERT INTO compras (cliente_id, data_compra, forma_pag, qtd_ticket, status_compra, total) VALUES (?,?,?,?,?,?) RETURNING id_compra");
            pgs.setInt(1, compra.getIdCliente());
            pgs.setDate(2, Date.valueOf(compra.getDataCompra()));
            pgs.setString(3, compra.getFormaPagamento());
            pgs.setInt(4, compra.getQuantidadeTickets());
            pgs.setObject(5, compra.getStatusCompra(), Types.OTHER);
            pgs.setDouble(6, compra.getTotal());

            ResultSet rs = pgs.executeQuery();
            if (rs.next()) {
                generatedId = rs.getInt("id_compra");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Inserir: " + e);
        }
        return generatedId;
    }

    @Override
    public void updateCompra(Compra compra) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE compras SET cliente_id=?, data_compra=?, forma_pag=?, qtd_ticket=?, status_compra=?, total=? WHERE id_compra=?");
            ps.setInt(1, compra.getIdCliente());
            ps.setDate(2, Date.valueOf(compra.getDataCompra()));
            ps.setString(3, compra.getFormaPagamento());
            ps.setInt(4, compra.getQuantidadeTickets());
            ps.setString(5, compra.getStatusCompra());
            ps.setDouble(6, compra.getTotal());
            ps.setInt(7, compra.getIdCompra());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar: " + e);
        }
    }

    @Override
    public void deleteCompra(int idcompra) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM compras WHERE id_compra=?");
            ps.setInt(1, idcompra);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e);
        }
    }

    @Override
    public Compra getCompraById(int idcompra) {
        Compra compra = new Compra();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM compras WHERE id_compra=?");

            preparedStatement.setInt(1, idcompra);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setIdCliente(rs.getInt("cliente_id"));
                compra.setDataCompra(rs.getDate("data_compra").toLocalDate());
                compra.setFormaPagamento(rs.getString("forma_pag"));
                compra.setQuantidadeTickets(rs.getInt("qtd_ticket"));
                compra.setStatusCompra(rs.getString("status_compra"));
                compra.setTotal(rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar por Id: " + e);
        }
        return compra;
    }

    @Override
    public List<Compra> getComprasByUserId(int cliente_id) {
        List<Compra> compras = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM compras WHERE cliente_id=?");
            preparedStatement.setInt(1, cliente_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setIdCliente(rs.getInt("cliente_id"));
                compra.setDataCompra(rs.getDate("data_compra").toLocalDate());
                compra.setFormaPagamento(rs.getString("forma_pag"));
                compra.setQuantidadeTickets(rs.getInt("qtd_ticket"));
                compra.setStatusCompra(rs.getString("status_compra"));
                compra.setTotal(rs.getDouble("total"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar compras por Id do cliente: " + e);
        }

        return compras;
    }

    @Override
    public List<Compra> getAllCompras() {
        List<Compra> compras = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM compras ORDER BY id_compra");
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setIdTicket(rs.getInt("ticket_id"));
                compra.setIdCliente(rs.getInt("cliente_id"));
                compra.setDataCompra(rs.getDate("data_compra").toLocalDate());
                compra.setFormaPagamento(rs.getString("forma_pag"));
                compra.setQuantidadeTickets(rs.getInt("qtd_ticket"));
                compra.setStatusCompra(rs.getString("status_compra"));
                compra.setTotal(rs.getDouble("total"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Listar: " + e);
        }
        return compras;
    }
}
