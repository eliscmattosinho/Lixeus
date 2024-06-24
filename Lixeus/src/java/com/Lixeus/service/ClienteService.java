/*
 * Autor: Elis Mattosinho
 * Descrição: Serviço para gerenciar operações CRUD para a entidade Cliente.
 */

package com.Lixeus.service;

import com.Lixeus.dao.ClienteDAO;
import com.Lixeus.dao.ClienteDAOImpl;
import com.Lixeus.model.Cliente;
import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService() throws SQLException, ClassNotFoundException {
        clienteDAO = new ClienteDAOImpl();
    }

    public void insertCliente(Cliente cliente) throws SQLException {
        clienteDAO.insertCliente(cliente);
    }

    public Cliente getClienteById(int id) throws SQLException {
        return clienteDAO.getClienteById(id);
    }

    public Cliente getClienteByEmailPassword(String email, String senha) throws SQLException {
        return clienteDAO.getClienteByEmailPassword(email, senha);
    }

    public Cliente getClienteByEmail(String email) throws SQLException {
        return clienteDAO.getClienteByEmail(email);
    }

    public List<Cliente> getAllClientes() throws SQLException {
        return clienteDAO.getAllClientes();
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        clienteDAO.updateCliente(cliente);
    }

    public void deleteCliente(int id) throws SQLException {
        clienteDAO.deleteCliente(id);
    }
}

