/*
 * Author: Elis Mattosinho
 * Descrição: Implementação da interface ClienteDAO para operações CRUD com a entidade Cliente.
 */

package com.Lixeus.dao;

import com.Lixeus.model.Cliente;
import com.Lixeus.util.Conexao;
import com.Lixeus.util.PasswordUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private final Connection connection;

    public ClienteDAOImpl() throws SQLException {
        connection = Conexao.estabeleceConexao();
    }

    @Override
    public void insertCliente(Cliente cliente) {
        try {
            String hashedSenha = PasswordUtils.hashPassword(cliente.getSenha());

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO clientes (nome, email, cpf, tel, data_nasc, senha) VALUES (?,?,?,?,?,?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getTel());
            ps.setDate(5, Date.valueOf(cliente.getData_nasc()));
            ps.setString(6, hashedSenha);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro ao Inserir Cliente: " + e);
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {
        try {
            if (cliente.getSenha() != null && !cliente.getSenha().isEmpty()) {
                String hashedSenha = PasswordUtils.hashPassword(cliente.getSenha());

                PreparedStatement ps = connection.prepareStatement(
                        "UPDATE clientes SET nome=?, email=?, cpf=?, tel=?, data_nasc=?, senha=? WHERE id_cliente=?");
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getEmail());
                ps.setString(3, cliente.getCpf());
                ps.setString(4, cliente.getTel());
                ps.setDate(5, Date.valueOf(cliente.getData_nasc()));
                ps.setString(6, hashedSenha);
                ps.setInt(7, cliente.getId_cliente());

                ps.executeUpdate();
                
            } else {
                PreparedStatement ps = connection.prepareStatement(
                        "UPDATE clientes SET nome=?, email=?, cpf=?, tel=?, data_nasc=? WHERE id_cliente=?");
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getEmail());
                ps.setString(3, cliente.getCpf());
                ps.setString(4, cliente.getTel());
                ps.setDate(5, Date.valueOf(cliente.getData_nasc()));
                ps.setInt(6, cliente.getId_cliente());

                ps.executeUpdate();
                
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar Cliente: " + e);
        }
    }

    @Override
    public void deleteCliente(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE clientes SET ativo = FALSE WHERE id_cliente=?");
            ps.setInt(1, id);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir Cliente: " + e);
        }
    }

    @Override
    public Cliente getClienteById(int id) {
        Cliente cliente = new Cliente();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM clientes WHERE ativo = TRUE AND id_cliente=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTel(rs.getString("tel"));
                cliente.setData_nasc(rs.getDate("data_nasc").toLocalDate());
                cliente.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar Cliente por Id: " + e);
        }
        return cliente;
    }

    @Override
    public Cliente getClienteByEmailPassword(String email, String hashedSenha) throws SQLException {
        Cliente cliente = null;

        // Consulta SQL para buscar o cliente pelo email
        String query = "SELECT * FROM clientes WHERE ativo = TRUE AND email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            // Recuperar o hash da senha armazenada no banco de dados
            String storedHashedSenha = rs.getString("senha");

            // Verificar se o hash da senha fornecida pelo usuário corresponde ao hash
            // armazenado
            if (hashedSenha.equals(storedHashedSenha)) {
                cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTel(rs.getString("tel"));
                cliente.setData_nasc(rs.getDate("data_nasc").toLocalDate());
            }
        }

        rs.close();
        preparedStatement.close();

        return cliente;
    }

    @Override
    public Cliente getClienteByEmail(String email) throws SQLException {
        Cliente cliente = null;

        // Consulta SQL para buscar o cliente pelo email
        String query = "SELECT * FROM clientes WHERE email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId_cliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setTel(rs.getString("tel"));
            cliente.setData_nasc(rs.getDate("data_nasc").toLocalDate());
        }

        rs.close();
        preparedStatement.close();

        return cliente;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM clientes ORDER BY id_cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTel(rs.getString("tel"));
                cliente.setData_nasc(rs.getDate("data_nasc").toLocalDate());
                clientes.add(cliente);
                cliente.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Listar Clientes: " + e);
        }
        return clientes;
    }
}
