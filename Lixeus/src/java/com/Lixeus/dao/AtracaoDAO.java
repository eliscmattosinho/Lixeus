/*
* Author: Matheus Maqueda
 Esse arquivo descreve as operações (funções) de acesso ao banco de dados
 */

package com.Lixeus.dao;

import com.Lixeus.model.Atracao;
import java.util.List;

public interface AtracaoDAO {

    void insertAtracao(Atracao atracao);

    void updateAtracao(Atracao atracao);

    void deleteAtracao(int id);

    Atracao getAtracaoById(int id);

    List<Atracao> listAtracoes();

    List<Atracao> getLatestAtracoes(int limit);

    List<Atracao> getAtracoesByCompraId(int id);
}
