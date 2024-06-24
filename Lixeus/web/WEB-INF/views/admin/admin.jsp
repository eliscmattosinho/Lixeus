<!--
    Author: Matheus Maqueda
    View admin
-->

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.Lixeus.model.Atracao"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="../static/css/global.css">
    <link rel="stylesheet" type="text/css" href="../static/css/admin.css">
    <title>Lixeus - Administração</title>
</head>
<body>
    <%
        // Verifica se os parâmetros "insert" ou "update" foram enviados na requisição
        String insertParam = request.getParameter("insert");
        String updateParam = request.getParameter("update");
        String deleteParam = request.getParameter("delete");
        String cancelParam = request.getParameter("cancel");
        if ((insertParam != null && !insertParam.isEmpty()) || (updateParam != null && !updateParam.isEmpty()) || (deleteParam != null && !deleteParam.isEmpty()) || (cancelParam != null && !cancelParam.isEmpty())) {
            String message = "";
            if (insertParam != null && !insertParam.isEmpty()) {
                int insert = Integer.parseInt(insertParam);
                if (insert == 1) {
                    message = "Atração inserida com sucesso!";
                }
            }
            if (updateParam != null && !updateParam.isEmpty()) {
                int update = Integer.parseInt(updateParam);
                if (update == 1) {
                    message = "Evento atualizado com sucesso!";
                }
            }
            if (deleteParam != null && !deleteParam.isEmpty()) {
                int delete = Integer.parseInt(deleteParam);
                if (delete == 1) {
                    message = "Evento deletado com sucesso!";
                }
            } %>
            <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        showForm('Consultar');
                    });
            </script>
            <% if (!message.isEmpty()) { %>
                <div class="alert alert-light alert-dismissible fade show z-2 text-center fs-5 w-100" role="alert">
                    <%= message %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
    <%
            }
        }
    %>
    <div class="position-relative">
        <img class="img-fluid logoAdmin" src="../static/img/lixeus-logo.png" alt="Logo">
        <h2 class="displayTitle">Selecione uma Ação</h2>
    </div>
    <div class="menu">
        <a href="#formIncluir"><button class="menuBtn"id="btnIncluir" onclick="showForm('Incluir')">Incluir</button></a>
        <a href="#formConsultar"><button class="menuBtn" id="btnConsultar" onclick="showForm('Consultar')">Consultar</button></a>
    </div>

    <%-- Form de inserção de atrações --%>
    <div class="forms">
        <form action="<%= request.getContextPath() %>/admin/atracao?action=insert" method="post" id="formIncluir" class="formAtracao">
            <div class="containerForm">
                <h2 class="formTitle">Incluir Atração</h2>
                <label for="inTituloAtracao">Título:</label>
                <input type="text" id="inTituloAtracao" name="inTituloAtracao" placeholder="Título">
                <label for="inDescAtracao">Descrição (Máx. 300):</label>
                <textarea name="inDescAtracao" id="inDescAtracao" placeholder="Descrição"></textarea>
                <label for="inPrecoTicket">Preço do ticket:</label>
                <input type="number" id="inPrecoTicket" name="inPrecoTicket" step="0.01" min="0" placeholder="0,00">
                <label for="inDataAtracao">Data:</label>
                <input type="date" id="inDataAtracao" name="inDataAtracao">
                <label for="inHoraInicioAtracao">Hora de Início:</label>
                <input type="time" id="inHoraInicioAtracao" name="inHoraInicioAtracao">
                <label for="inHoraFimAtracao">Hora de Término:</label>
                <input type="time" id="inHoraFimAtracao" name="inHoraFimAtracao">
                <label for="inMaxTicket">Lotação Máxima:</label>
                <input type="number" id="inMaxTicket" name="inMaxTicket" min="0" placeholder="0">
                <!-- Vai ter lotação máxima por cliente? o que é isso? preciso entender pra por... -->
                <button class="botao btnBlack">Adicionar</button>
            </div>
        </form>

        <%-- Display das atrações em forma de tabela ao consultar --%>
        <div id="formConsultar" class="containerConsultar formAtracao">
            <h2 class="formTitle">Atrações</h2>
            <table class="table table-striped tableConsultar">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th class="colTitulo">Título</th>
                        <th class="colDescricao">Descrição</th>
                        <th>Preço do Ticket</th>
                        <th class="colData">Data</th>
                        <th class="colHoraIni">Hora de Início</th>
                        <th>Hora de Término</th>
                        <th>Lotação Máxima</th>
                        <th class="colBtn">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <% DecimalFormat df = new DecimalFormat("#,##0.00"); 
                       List<Atracao> atracoes = (List<Atracao>) request.getAttribute("listAtracao");
                    if (atracoes != null) {
                        for (Atracao atracao : atracoes) { %>
                        <tr id="row-<%= atracao.getId() %>">
                            <td><%= atracao.getId() %></td>
                            <td class="wordBreak" id="titulo-<%= atracao.getId() %>"><%= atracao.getTitulo() %></td>
                            <td class="wordBreak" data-descricao="<%= atracao.getDescricao() %>" id="descricao-<%= atracao.getId() %>"><% String descricao = atracao.getDescricao(); if (descricao.length() > 65) { descricao = descricao.substring(0, 65).trim() + "...";} %><%= descricao %><% if (atracao.getDescricao().length() > 50) { %><span role="button" onclick="showDesc('<%= atracao.getDescricao() %>')"> <u>Ver Descrição Completa</u></span><% } %></td>
                            <td id="preco-<%= atracao.getId() %>">R$<%= df.format(atracao.getPreco_ticket()) %></td>
                            <td id="data-<%= atracao.getId() %>"><%= atracao.getData() %></td>
                            <td id="horaIni-<%= atracao.getId() %>"><%= atracao.getHora_ini() %></td>
                            <td id="horaFim-<%= atracao.getId() %>"><%= atracao.getHora_fim() %></td>
                            <td id="maxTicket-<%= atracao.getId() %>"><%= atracao.getMax_ticket() %></td>
                            <td>
                                <span onclick="enableEdit(<%= atracao.getId() %>, '<%= request.getContextPath() %>')" class="btnTable mb-1 bi bi-pencil-square"></span>
                                <span onclick="confirmDelete(<%= atracao.getId() %>, '<%= request.getContextPath() %>')" class="btnTable btn bi bi-trash3-fill"></span>
                            </td>
                        </tr>
                    <% }
                    } %>
                </tbody>
            </table>
        </div>
    </div>
    <script src="../static/js/admin.js"></script>           
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>