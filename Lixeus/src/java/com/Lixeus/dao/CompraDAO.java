/*
 * Author: Elis Mattosinho
 * Descrição: Interface de DAO para operações CRUD com a entidade Compra.
 */

package com.Lixeus.dao;

import com.Lixeus.model.Compra;
import java.util.List;

public interface CompraDAO {

    // MÉTODOS DE ALTERAÇÃO DE DADOS
    int createCompra(Compra compra);
    void updateCompra(Compra compra);
    void deleteCompra(int idcompra);

    // MÉTODOS DE BUSCA DE COMPRA
    Compra getCompraById(int idcompra);
    List<Compra> getComprasByUserId(int cliente_id);
    List<Compra> getAllCompras();
}
