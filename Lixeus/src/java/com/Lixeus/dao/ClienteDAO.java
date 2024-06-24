/*
 * Author: Elis Mattosinho
 * Descrição: Interface de DAO para operações CRUD com a entidade Cliente.
 */

package com.Lixeus.dao;

import com.Lixeus.model.Cliente;
import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    
    // MÉTODOS DE ALTERAÇÃO DE DADOS
    void insertCliente(Cliente cliente) throws SQLException;
    void updateCliente(Cliente cliente) throws SQLException;
    void deleteCliente(int id) throws SQLException;

    // MÉTODOS DE BUSCA DE CLIENTE
    Cliente getClienteById(int id) throws SQLException;
    Cliente getClienteByEmailPassword(String email, String senha) throws SQLException;
    Cliente getClienteByEmail(String email) throws SQLException;
    List<Cliente> getAllClientes() throws SQLException;
}
