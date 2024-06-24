package com.Lixeus.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Lixeus.dao.AtracaoDAOImpl;
import com.Lixeus.model.Atracao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaderServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/views/user/";
        String action = request.getParameter("action");
        
        if (action == null || action.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/404.jsp");
            return;
        }

        switch (action) {
            case "loginCadastro" -> url += "login_cadastro.jsp";
            case "verPerfil" -> url += "profile.jsp";
            case "landingPage" -> url = "/atracao?action=landingPage";
            case "verAtracoes" -> url = "/atracao?action=verAtracoes";
            case "verAtracao" -> url = "/atracao?action=verAtracao&id=" + request.getParameter("id");
            case "formCompra" -> url = "/compra?action=formCompra&id=" + request.getParameter("id");
            case "verCompras" -> url = "/compra?action=verCompras";
            case "verCompra" -> url = "/compra?action=verCompra";
            case "verTicket" -> url = "/ticket?action=verTicket&id=" + request.getParameter("id");
            default -> url = "/WEB-INF/error/404.jsp";
        }

    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
