<!--
    Author: Matheus Maqueda
    View Atração
-->

<%@page import="java.time.format.DateTimeFormatter"%> <%@page
import="java.util.List"%> <%@page import="com.Lixeus.model.Atracao"%> <%@ page
contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  </head>
  <body>
    <section class="parallax mt-3">
      <h2 class="titulo tituloBranco">Atração</h2>
      <% Atracao atracao = (Atracao) request.getAttribute("atracao"); %>
      <div class="container mt-5">
        <div class="card">
          <div class="card-header">
            <h2><%= atracao.getTitulo() %></h2>
          </div>
          <div class="card-body">
            <p class="card-text">
              <strong>Descrição:</strong> <%= atracao.getDescricao() %>
            </p>
            <p class="card-text">
              <strong>Preço Do Ticket:</strong> R$ <%= atracao.getPreco_ticket() %>
            </p>
            <p class="card-text">
              <strong>Data:</strong> <%=
              atracao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
              %>
            </p>
            <p class="card-text">
              <strong>Hora de Início:</strong> <%= atracao.getHora_ini() %>
            </p>
            <p class="card-text">
              <strong>Hora de Fim:</strong> <%= atracao.getHora_fim() %>
            </p>
            <p class="card-text">
              <strong>Ingressos Restantes:</strong> <%= atracao.getMax_ticket()
              %>
            </p>
            <!-- Author: Elis Mattosinho
                 Descrição: Checar a sessão antes de permitir form de cadastro utilizando JSTL -->
            <%-- Botão "Comprar Ingressos" ou "Faça login para comprar!" --%>
            <c:choose>
              <c:when test="${not empty sessionScope.cliente}">
                <a href="/Lixeus/LoaderServlet?action=formCompra&id=<%= atracao.getId() %>">
                  <button class="btn btn-dark">Comprar Ingressos</button>
                </a>
              </c:when>
              <c:otherwise>
                <a href="index.jsp">
                  <button class="btn btn-dark">Faça login para comprar!</button>
                </a>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
    </section>
  </body>
</html>
