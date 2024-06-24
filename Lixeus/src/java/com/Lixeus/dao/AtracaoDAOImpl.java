/*
* Author: Matheus Maqueda
 Esse arquivo compõe a lógica das operações no banco de dados relacionados a atrações
*/

package com.Lixeus.dao;

import com.Lixeus.model.Atracao;
import com.Lixeus.util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtracaoDAOImpl implements AtracaoDAO {
    private final Connection connection;

    public AtracaoDAOImpl() throws SQLException {
        connection = Conexao.estabeleceConexao();
    }

    @Override
    public void insertAtracao(Atracao atracao) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO atracoes (titulo, descricao, preco_ticket, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, atracao.getTitulo());
            ps.setString(2, atracao.getDescricao());
            ps.setDouble(3, atracao.getPreco_ticket());
            ps.setDate(4, Date.valueOf(atracao.getData()));
            ps.setTime(5, Time.valueOf(atracao.getHora_ini()));
            ps.setTime(6, Time.valueOf(atracao.getHora_fim()));
            ps.setInt(7, atracao.getMax_ticket());
            ps.setInt(8, atracao.getMax_cliente_ticket());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Inserir: " + e);
        }
    }

    @Override
    public void updateAtracao(Atracao atracao) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE atracoes SET titulo=?, descricao=?, preco_ticket=?, data=?, hora_ini=?, hora_fim=?, max_ticket=?, max_cliente_ticket=? WHERE id_atracao=?");
            ps.setString(1, atracao.getTitulo());
            ps.setString(2, atracao.getDescricao());
            ps.setDouble(3, atracao.getPreco_ticket());
            ps.setDate(4, Date.valueOf(atracao.getData()));
            ps.setTime(5, Time.valueOf(atracao.getHora_ini()));
            ps.setTime(6, Time.valueOf(atracao.getHora_fim()));
            ps.setInt(7, atracao.getMax_ticket());
            ps.setInt(8, atracao.getMax_cliente_ticket());
            ps.setInt(9, atracao.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar: " + e);
        }
    }

    @Override
    public void deleteAtracao(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM atracoes WHERE id_atracao=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e);
        }
    }

    @Override
    public Atracao getAtracaoById(int id) {
        Atracao atracao = new Atracao();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM atracoes WHERE id_atracao=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                atracao.setId(rs.getInt("id_atracao"));
                atracao.setTitulo(rs.getString("titulo"));
                atracao.setDescricao(rs.getString("descricao"));
                atracao.setPreco_ticket(rs.getDouble("preco_ticket"));
                atracao.setData(rs.getDate("data").toLocalDate());
                atracao.setHora_ini(rs.getTime("hora_ini").toLocalTime());
                atracao.setHora_fim(rs.getTime("hora_fim").toLocalTime());
                atracao.setMax_ticket(rs.getInt("max_ticket"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar por Id: " + e);
        }
        return atracao;
    }

    @Override
    public List<Atracao> listAtracoes() {
        List<Atracao> atracoes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM atracoes ORDER BY id_atracao");
            while (rs.next()) {
                Atracao atracao = new Atracao();
                atracao.setId(rs.getInt("id_atracao"));
                atracao.setTitulo(rs.getString("titulo"));
                atracao.setDescricao(rs.getString("descricao"));
                atracao.setPreco_ticket(rs.getDouble("preco_ticket"));
                atracao.setData(rs.getDate("data").toLocalDate());
                atracao.setHora_ini(rs.getTime("hora_ini").toLocalTime());
                atracao.setHora_fim(rs.getTime("hora_fim").toLocalTime());
                atracao.setMax_ticket(rs.getInt("max_ticket"));
                atracoes.add(atracao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Listar: " + e);
        }
        return atracoes;
    }

    @Override
    public List<Atracao> getLatestAtracoes(int limit) {
        List<Atracao> atracoes = new ArrayList<>();
        String sql = "SELECT * FROM atracoes ORDER BY id_atracao DESC LIMIT ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Atracao atracao = new Atracao();
                atracao.setId(rs.getInt("id_atracao"));
                atracao.setTitulo(rs.getString("titulo"));
                atracao.setDescricao(rs.getString("descricao"));
                atracoes.add(atracao);
            }
        } catch (SQLException e) {
            System.out.println("Falha ao Consultar Ultimas Atracoes: " + e);
        }
        return atracoes;
    }

    /*
    * Author: Elis Mattosinho
    * Descrição: Esta função retorna uma lista de objetos Atracao que estão associados a uma compra específica.
    */
    @Override
    public List<Atracao> getAtracoesByCompraId(int compraId) {
        List<Atracao> atracoes = new ArrayList<>();
        String sql = "SELECT a.* FROM atracoes a JOIN tickets t ON a.id_atracao = t.atracao_id JOIN ticketsporcompra tc ON t.id_ticket = tc.ticket_id WHERE tc.compra_id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, compraId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Atracao atracao = new Atracao();
                atracao.setId(rs.getInt("id_atracao"));
                atracao.setTitulo(rs.getString("titulo"));
                atracao.setDescricao(rs.getString("descricao"));
                atracao.setPreco_ticket(rs.getDouble("preco_ticket"));
                atracao.setData(rs.getDate("data").toLocalDate());
                atracao.setHora_ini(rs.getTime("hora_ini").toLocalTime());
                atracao.setHora_fim(rs.getTime("hora_fim").toLocalTime());
                atracao.setMax_ticket(rs.getInt("max_ticket"));
                atracao.setMax_cliente_ticket(rs.getInt("max_cliente_ticket"));
                atracoes.add(atracao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar Atrações por Id de Compra: " + e);
        }
        return atracoes;
    }
}
