/*
* Author: Matheus Maqueda
* Esse arquivo é responsável por tratar as requisições HTTP relacionadas às atrações
*/

package com.Lixeus.servlet;

import com.Lixeus.model.Atracao;
import com.Lixeus.service.AtracaoService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.RequestDispatcher;

public class AtracaoServlet extends HttpServlet {
    private AtracaoService atracaoService;

    @Override
    public void init() throws ServletException {
        try {
            atracaoService = new AtracaoService();
        } catch (SQLException e) {
            throw new ServletException("Falha para inicializar AtracaoService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/admin/atracao" -> {
                String action = request.getParameter("action");
                if (action == null) {
                    action = "list";
                }
                switch (action) {
                    case "delete" -> deleteAtracao(request, response);
                    default -> listAtracao(request, response);
                }
            }
            case "/atracao" -> {
                String action = request.getParameter("action");
                switch (action) {
                    case "verAtracao" -> {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Atracao atracao = atracaoService.getAtracaoById(id);
                        request.setAttribute("atracao", atracao);
                        System.out.println(atracao.toString());
                        request.getRequestDispatcher("/WEB-INF/views/user/ver_atracao.jsp?page=getAtracao")
                                .forward(request, response);
                    }
                    case "landingPage" -> {
                        List<Atracao> latestAtracoes = atracaoService.getLatestAtracoes(3);
                        request.setAttribute("latestAtracoes", latestAtracoes);
                        request.getRequestDispatcher("/WEB-INF/views/user/landing_page.jsp?page=landingPage")
                                .forward(request, response);
                    }
                    case "verAtracoes" -> {
                        List<Atracao> allAtracoes = atracaoService.listAtracoes();
                        request.setAttribute("allAtracoes", allAtracoes);
                        request.getRequestDispatcher("/WEB-INF/views/user/ver_atracoes.jsp?page=allAtracoes")
                                .forward(request, response);
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "insert";
        }

        switch (action) {
            case "insert" -> insertAtracao(request, response);
            case "update" -> updateAtracao(request, response);
            default -> listAtracao(request, response);
        }
    }

    private void listAtracao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Atracao> listAtracao = atracaoService.listAtracoes();
        request.setAttribute("listAtracao", listAtracao);
        request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp").forward(request, response);
    }

    private void insertAtracao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String titulo = request.getParameter("inTituloAtracao");
        String descricao = request.getParameter("inDescAtracao");
        double preco = Double.parseDouble(request.getParameter("inPrecoTicket"));
        LocalDate data = LocalDate.parse(request.getParameter("inDataAtracao"));
        LocalTime hora_ini = LocalTime.parse(request.getParameter("inHoraInicioAtracao"));
        LocalTime hora_fim = LocalTime.parse(request.getParameter("inHoraFimAtracao"));
        int max_ticket = Integer.parseInt(request.getParameter("inMaxTicket"));

        Atracao newAtracao = new Atracao();
        newAtracao.setTitulo(titulo);
        newAtracao.setDescricao(descricao);
        newAtracao.setPreco_ticket(preco);
        newAtracao.setData(data);
        newAtracao.setHora_ini(hora_ini);
        newAtracao.setHora_fim(hora_fim);
        newAtracao.setMax_ticket(max_ticket);
        atracaoService.insertAtracao(newAtracao);
        response.sendRedirect("atracao?insert=1");
    }

    private void updateAtracao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("editTitulo");
        String descricao = request.getParameter("editDescricao");
        double preco = Double.parseDouble(request.getParameter("editPreco"));
        LocalDate data = LocalDate.parse(request.getParameter("editData"));
        LocalTime hora_ini = LocalTime.parse(request.getParameter("editHoraIni"));
        LocalTime hora_fim = LocalTime.parse(request.getParameter("editHoraFim"));
        int max_ticket = Integer.parseInt(request.getParameter("editMaxTicket"));

        Atracao atracao = new Atracao();
        atracao.setId(id);
        atracao.setTitulo(titulo);
        atracao.setDescricao(descricao);
        atracao.setPreco_ticket(preco);
        atracao.setData(data);
        atracao.setHora_ini(hora_ini);
        atracao.setHora_fim(hora_fim);
        atracao.setMax_ticket(max_ticket);
        atracaoService.updateAtracao(atracao);
        response.sendRedirect("atracao?update=1");
    }

    private void deleteAtracao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        atracaoService.deleteAtracao(id);
        response.sendRedirect("atracao?delete=1");
    }
}
