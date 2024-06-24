/*
 * Author: Elis Mattosinho
 * Descrição: Servlet para gerenciar o login dos clientes, incluindo verificação
 *            de credenciais e gerenciamento de sessão.
 */

package com.Lixeus.servlet;

import com.Lixeus.model.Cliente;
import com.Lixeus.service.ClienteService;
import com.Lixeus.util.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private ClienteService clienteService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            clienteService = new ClienteService();
        } catch (SQLException e) {
            throw new ServletException("Erro ao inicializar ClienteService", e);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Classe não encontrada", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("loginEmail");
        String senha = request.getParameter("loginSenha");

        // Criptografando a senha antes de verificar no banco de dados
        String hashedSenha = PasswordUtils.hashPassword(senha);

        try {
            Cliente cliente = clienteService.getClienteByEmailPassword(email, hashedSenha);

            if (cliente != null) {
                HttpSession session = request.getSession();
                session.setAttribute("cliente", cliente);
                session.setAttribute("clienteId", cliente.getId_cliente()); // Adiciona o ID do cliente na sessão
                response.sendRedirect("index.jsp");

                System.out.println("Cliente logado: " + cliente.getId_cliente());
            } else {
                Cliente clienteByEmail = clienteService.getClienteByEmail(email);
                if (clienteByEmail != null) {
                    // Senha incorreta
                    response.sendRedirect("LoaderServlet?action=loginCadastro&loginError=senha");
                } else {
                    // Email não existe na base de dados
                    response.sendRedirect("LoaderServlet?action=loginCadastro&loginError=email");
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao autenticar usuário", e);
        }
    }
}
