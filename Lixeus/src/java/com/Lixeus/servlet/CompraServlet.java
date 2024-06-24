/*
 * Author: Elis Mattosinho
 * Descrição: Servlet para gerenciar operações de compra, incluindo inserção,
 *            atualização, listagem e visualização de detalhes das compras.
 */

package com.Lixeus.servlet;

import com.Lixeus.model.Atracao;
import com.Lixeus.model.Compra;
import com.Lixeus.model.TicketCompra;
import com.Lixeus.model.Ticket;
import com.Lixeus.service.AtracaoService;
import com.Lixeus.service.CompraService;
import com.Lixeus.service.TicketCompraService;
import com.Lixeus.service.TicketService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompraServlet extends HttpServlet {
    private CompraService compraService;

    @Override
    public void init() throws ServletException {
        try {
            compraService = new CompraService();
        } catch (SQLException e) {
            throw new ServletException("Falha para inicializar CompraService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/compra" -> {
                String action = request.getParameter("action");
                if (action == null) {
                    action = "list";
                }
                switch (action) {
                    case "formCompra" -> {
                        // Pegando dados através da session
                        HttpSession session = request.getSession();
                        Integer clienteId = (Integer) session.getAttribute("clienteId");
                        String statusCompra = "Pendente";
                    
                        AtracaoService atracaoService = null;
                        try {
                            atracaoService = new AtracaoService();
                        } catch (SQLException ex) {
                            Logger.getLogger(CompraServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                        if (atracaoService != null) {
                            // Obter dados da atração para exibir no formulário
                            String idParam = request.getParameter("id");
                            if (idParam != null && !idParam.isEmpty()) {
                                try {
                                    int atracaoId = Integer.parseInt(idParam);
                                    Atracao atracao = atracaoService.getAtracaoById(atracaoId);
                                    request.setAttribute("atracao", atracao);
                                } catch (NumberFormatException ex) {
                                    Logger.getLogger(CompraServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                throw new ServletException("Parâmetro 'id' não encontrado na requisição.");
                            }
                        } else {
                            throw new ServletException("Falha ao inicializar o serviço de Atração.");
                        }
                    
                        request.setAttribute("clienteId", clienteId);
                        request.setAttribute("statusCompra", statusCompra);
                    
                        request.getRequestDispatcher("/WEB-INF/views/user/form_compra.jsp").forward(request, response);
                    }             

                    case "verCompras" -> {
                        listarComprasDoUsuario(request, response);
                    }
                    case "verCompra" -> {
                        verDetalhe(request, response);
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
            case "insert" -> {
                try {
                    insertCompra(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(CompraServlet.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ServletException("Erro ao realizar compra", ex);
                }
            }

            case "update" -> updateCompra(request, response);
            default -> listCompra(request, response);
        }
    }

    private void listCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Compra> listCompras;
        try {
            listCompras = compraService.getAllCompras();
        } catch (SQLException e) {
            throw new ServletException("Error retrieving list of purchases", e);
        }
        request.setAttribute("listCompras", listCompras);
        request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp").forward(request, response);
    }

    private void insertCompra(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        Integer clienteId = (Integer) request.getSession().getAttribute("clienteId");
        
        // Recuperar os parâmetros do formulário
        String forma_pag = request.getParameter("forma_pag");
        int qtd_ticket = Integer.parseInt(request.getParameter("qtd_ticket"));
        int atracaoId = Integer.parseInt(request.getParameter("atracaoId"));
    
        AtracaoService atracaoService = new AtracaoService();
        Atracao atracao = atracaoService.getAtracaoById(atracaoId);
    
        // Calcular o total da compra
        double precoUnitario = atracao.getPreco_ticket();
        double total = qtd_ticket * precoUnitario;
    
        // Criar um objeto Compra com os dados fornecidos
        Compra compra = new Compra();
        compra.setIdCliente(clienteId);
        compra.setDataCompra(LocalDate.now());
        compra.setFormaPagamento(forma_pag);
        compra.setQuantidadeTickets(qtd_ticket);
        compra.setStatusCompra("confirmando");
        compra.setTotal(total);
    
        // Salva a compra no banco de dados
        CompraService compraService = new CompraService();
        int compraId = compraService.createCompra(compra);
    
        // Inserir tickets e associar com a compra
        for (int i = 0; i < qtd_ticket; i++) {
            Ticket ticket = new Ticket();
            ticket.setIdAtracao(atracaoId);
            ticket.setStatus("disponível");
            ticket.setIdCompra(compraId);

            TicketService ticketService = new TicketService();
            int ticketId = ticketService.insertTicket(ticket);
            System.out.println("Ticket ID inserido: " + ticketId);
    
            TicketCompra ticketCompra = new TicketCompra();
            ticketCompra.setIdTicket(ticketId);
            ticketCompra.setIdCompra(compraId);
            ticketCompra.setTicket(ticket);

            TicketCompraService ticketCompraService = new TicketCompraService();
            ticketCompraService.addTicketCompra(ticketCompra);
        }
    
        // Redirecionar o usuário para a página de visualização de compras
        response.sendRedirect("LoaderServlet?action=verCompras");
    }

    private void updateCompra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int ticketId = Integer.parseInt(request.getParameter("editTicketId"));
        int clienteId = Integer.parseInt(request.getParameter("editClienteId"));
        LocalDate dataCompra = LocalDate.parse(request.getParameter("editDataCompra"));
        String formaPag = request.getParameter("editFormaPag");
        int qtdTicket = Integer.parseInt(request.getParameter("editQtdTicket"));
        String statusCompra = request.getParameter("editStatusCompra");
        double total = Double.parseDouble(request.getParameter("editTotal"));

        Compra compra = new Compra();
        compra.setIdCompra(id);
        compra.setIdTicket(ticketId);
        compra.setIdCliente(clienteId);
        compra.setDataCompra(dataCompra);
        compra.setFormaPagamento(formaPag);
        compra.setQuantidadeTickets(qtdTicket);
        compra.setStatusCompra(statusCompra);
        compra.setTotal(total);

        try {
            compraService.updateCompra(compra);
        } catch (SQLException e) {
            throw new IOException("Erro compra", e);
        }
        response.sendRedirect("compra?action=update");
    }

    private void listarComprasDoUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer clienteId = (Integer) session.getAttribute("clienteId");

        if (clienteId == null) {
            request.setAttribute("comprasDoUsuario", Collections.emptyList());
        } else {
            try {
                List<Compra> comprasDoUsuario = compraService.getComprasByUserId(clienteId);
                request.setAttribute("comprasDoUsuario", comprasDoUsuario);
            } catch (SQLException e) {
                request.setAttribute("comprasDoUsuario", Collections.emptyList());
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/user/detalhes_compras.jsp").forward(request, response);
    }

    private void verDetalhe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int compraId = Integer.parseInt(request.getParameter("id"));
        try {
            Compra compra = compraService.getCompraById(compraId);
            List<Atracao> atracoes = compraService.getAtracoesByCompraId(compraId);
    
            request.setAttribute("compra", compra);
            request.setAttribute("atracoes", atracoes);
            request.getRequestDispatcher("/WEB-INF/views/user/detalhe_compra.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar detalhes da compra", e);
        }
    }     
}
