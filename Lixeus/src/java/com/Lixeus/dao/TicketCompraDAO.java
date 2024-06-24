/*
 * Author: Elis Mattosinho
 * Descrição: Interface de DAO para operações CRUD com a entidade TicketCompra.
 */

package com.Lixeus.dao;

import com.Lixeus.model.TicketCompra;
import java.sql.SQLException;
import java.util.List;

public interface TicketCompraDAO {

    // MÉTODOS DE ALTERAÇÃO DE DADOS
    void addTicketCompra(TicketCompra ticketCompra) throws SQLException;
    void updateTicketCompra(TicketCompra ticketCompra) throws SQLException;
    void deleteTicketCompra(int idTicketCompra) throws SQLException;

    // MÉTODOS DE BUSCA DE TICKET(S) DA COMPRA
    List<TicketCompra> getTicketComprasByCompraId(int idCompra) throws SQLException;
    List<TicketCompra> getTicketComprasByUserId(int cliente_id) throws SQLException;
}
