/*
 * Author: Elis Mattosinho
 * Descrição: Servlet para gerenciar os tickets, incluindo inserção, atualização,
 *            listagem e visualização de tickets relacionados às compras.
 */

package com.Lixeus.servlet;

import com.Lixeus.dao.AtracaoDAO;
import com.Lixeus.dao.AtracaoDAOImpl;
import com.Lixeus.model.Atracao;
import com.Lixeus.model.Ticket;
import com.Lixeus.service.TicketService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketServlet extends HttpServlet {
    private TicketService ticketService;
    private AtracaoDAO atracaoDAO;

    @Override
    public void init() throws ServletException {
        try {
            ticketService = new TicketService();
            atracaoDAO = new AtracaoDAOImpl();
        } catch (SQLException e) {
            throw new ServletException("Falhou inicializar TicketService ou AtracaoDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/ticket" -> {
                String action = request.getParameter("action");
                    if (action == null) {
                        action = "list";
                    }
                        switch (action) {
                            case "verTicket":
                                try {
                                    verTickets(request, response);
                                } catch (SQLException e) {
                                    throw new ServletException("Erro ao buscar tickets", e);
                                }
                                break;
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
            case "insert" -> insertTicket(request, response);
            case "update" -> updateTicket(request, response);
            default -> listTicket(request, response);
        }
    }

    private void listTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ticket> listTicket = ticketService.listTickets();
        request.setAttribute("listTicket", listTicket);
        request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp").forward(request, response);
    }

    private void insertTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int atracaoId = Integer.parseInt(request.getParameter("inAtracaoId"));
        String status = request.getParameter("inStatus");

        Ticket ticket = new Ticket();
        ticket.setIdAtracao(atracaoId);
        ticket.setStatus(status);
        ticketService.insertTicket(ticket);
        response.sendRedirect("ticket?insert=1");
    }

    private void updateTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int atracaoId = Integer.parseInt(request.getParameter("editAtracaoId"));
        String status = request.getParameter("inStatus");

        Ticket ticket = new Ticket();
        ticket.setIdTicket(id);
        ticket.setIdAtracao(atracaoId);
        ticket.setStatus(status);
        ticketService.updateTicket(ticket);
        response.sendRedirect("ticket?update=1");
    }

    private void deleteTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ticketService.deleteTicket(id);
        response.sendRedirect("ticket?delete=1");
    }

    private void verTickets(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int compraId = Integer.parseInt(request.getParameter("id"));
    
        List<Ticket> tickets = ticketService.getTicketsByCompra(compraId);
        request.setAttribute("tickets", tickets);
        System.out.println(tickets);
    
        List<Atracao> atracoes = atracaoDAO.getAtracoesByCompraId(compraId);
        request.setAttribute("atracoes", atracoes);
        System.out.println(atracoes);

        request.getRequestDispatcher("/WEB-INF/views/user/ticket.jsp").forward(request, response);
    }
}
