/*
 * Author: Elis Mattosinho
 * Descrição: Servlet para gerenciar operações CRUD para a entidade Cliente, 
 *            incluindo cadastro, atualização, exclusão e listagem de clientes.
 */

package com.Lixeus.servlet;

import com.Lixeus.model.Cliente;
import com.Lixeus.service.ClienteService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class ClienteServlet extends HttpServlet {
    private ClienteService clienteService;

    @Override
    public void init() throws ServletException {
        try {
            clienteService = new ClienteService();
        } catch (SQLException e) {
            throw new ServletException("Falha para inicializar ClienteService", e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "delete" -> {
                try {
                    deleteCliente(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            default -> {
                try {
                    listCliente(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
                    insertCliente(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case "update" -> {
                try {
                    updateCliente(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case "delete" -> {
                try {
                    deleteCliente(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case "updateProfile" -> {
                try {
                    updateProfileCliente(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case "logout" -> logoutCliente(request, response);

            default -> {
                try {
                    listCliente(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void listCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Cliente> listCliente = clienteService.getAllClientes();
        request.setAttribute("listCliente", listCliente);
        request.getRequestDispatcher("/WEB-INF/views/user/admin_cliente.jsp").forward(request, response);
    }

    private void insertCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String tel = request.getParameter("tel");
        LocalDate data_nasc = LocalDate.parse(request.getParameter("data_nasc"));

        Cliente newCliente = new Cliente();
        newCliente.setNome(nome);
        newCliente.setEmail(email);
        newCliente.setCpf(cpf);
        newCliente.setTel(tel);
        newCliente.setData_nasc(data_nasc);
        clienteService.insertCliente(newCliente);
        response.sendRedirect("cliente?action=list");
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id_cliente"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String tel = request.getParameter("tel");
        LocalDate data_nasc = LocalDate.parse(request.getParameter("data_nasc"));
        String senha = request.getParameter("senha");

        Cliente cliente = clienteService.getClienteById(id);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setTel(tel);
        cliente.setData_nasc(data_nasc);

        if (senha != null && !senha.isEmpty()) {
            cliente.setSenha(senha);
        }

        clienteService.updateCliente(cliente);
        response.sendRedirect("cliente?action=perfil");
    }

    private void updateProfileCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id_cliente"));
        Cliente cliente = clienteService.getClienteById(id);

        String nome = request.getParameter("nome");
        if (nome != null && !nome.isEmpty()) {
            cliente.setNome(nome);
        }

        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            cliente.setEmail(email);
        }

        String cpf = request.getParameter("cpf");
        if (cpf != null && !cpf.isEmpty()) {
            cliente.setCpf(cpf);
        }

        String tel = request.getParameter("tel");
        if (tel != null && !tel.isEmpty()) {
            cliente.setTel(tel);
        }

        String dataNascStr = request.getParameter("data_nasc");
        if (dataNascStr != null && !dataNascStr.isEmpty()) {
            LocalDate data_nasc = LocalDate.parse(dataNascStr);
            cliente.setData_nasc(data_nasc);
        }

        String senha = request.getParameter("senha");
        if (senha != null && !senha.isEmpty()) {
            cliente.setSenha(senha);
        }

        clienteService.updateCliente(cliente);

        // Atualiza o objeto 'cliente' na sessão
        request.getSession().setAttribute("cliente", cliente);

        response.sendRedirect("index.jsp");
    }

    private void deleteCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id_cliente"));
        clienteService.deleteCliente(id);

        // Encerra a sessão do usuário após excluir a conta
        request.getSession().invalidate();

        // Redireciona para a página inicial
        response.sendRedirect("index.jsp");
    }

    private void logoutCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}
