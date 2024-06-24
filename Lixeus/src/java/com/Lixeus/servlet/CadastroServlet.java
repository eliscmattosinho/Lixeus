/*
 * Author: Elis Mattosinho
 * Descrição: Servlet para gerenciar o cadastro de novos clientes.
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

public class CadastroServlet extends HttpServlet {

    private ClienteService clienteService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            clienteService = new ClienteService();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Erro ao inicializar ClienteService", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("cadastroNome");
        String email = request.getParameter("cadastroEmail");
        String senha = request.getParameter("cadastroSenha");
        String cpf = request.getParameter("cadastroCPF");
        String tel = request.getParameter("cadastroTel");
        LocalDate dataNasc = LocalDate.parse(request.getParameter("cadastroDataNasc"));

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setEmail(email);
        novoCliente.setSenha(senha);
        novoCliente.setCpf(cpf);
        novoCliente.setTel(tel);
        novoCliente.setData_nasc(dataNasc);

        try {
            clienteService.insertCliente(novoCliente);

            request.setAttribute("cadastroSuccess", "Cadastro realizado com sucesso!");

            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            throw new ServletException("Erro ao cadastrar cliente", e);
        }
    }
}
