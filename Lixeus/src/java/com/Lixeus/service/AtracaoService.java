/*
* Author: Matheus Maqueda
 Esse arquivo é responsável pela lógica de serviços
*/

package com.Lixeus.service;

import com.Lixeus.dao.AtracaoDAO;
import com.Lixeus.dao.AtracaoDAOImpl;
import com.Lixeus.model.Atracao;
import java.sql.SQLException;
import java.util.List;

public class AtracaoService {
    private final AtracaoDAO atracaoDAO;

    public AtracaoService() throws SQLException {
        atracaoDAO = new AtracaoDAOImpl();
    }
    
    public void insertAtracao(Atracao atracao) {
        atracaoDAO.insertAtracao(atracao);
    }

    public void updateAtracao(Atracao atracao) {
        atracaoDAO.updateAtracao(atracao);
    }

    public void deleteAtracao(int id) {
        atracaoDAO.deleteAtracao(id);
    }
    
    public Atracao getAtracaoById(int id) {
        return atracaoDAO.getAtracaoById(id);
    }

    public List<Atracao> listAtracoes() {
        return atracaoDAO.listAtracoes();
    }
    
    public List<Atracao> getLatestAtracoes(int limit) {
        return atracaoDAO.getLatestAtracoes(limit);
    }

    public List<Atracao> getAtracoesByCompraId(int id) {
        return atracaoDAO.getAtracoesByCompraId(id);
    }
}
