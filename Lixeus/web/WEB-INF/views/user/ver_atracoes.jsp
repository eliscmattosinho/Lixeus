<!--
    Author: Matheus Maqueda
    View Atrações
-->


<%@page import="java.util.List"%>
<%@page import="com.Lixeus.model.Atracao"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <section class="parallax mt-3">
        <h2 class="titulo tituloBranco">Atrações</h2>
        <div class="cardsAtracoes">
        <%  List<Atracao> atracoes = (List<Atracao>)request.getAttribute("allAtracoes");
            if (atracoes.isEmpty()) { %>
                <h3 class="text-light">Não há atrações no momento</h3>
            <% } else {
                for(Atracao atracao:atracoes) { %>
                <figure class="cardAtracao" onclick="loadPage('/LoaderServlet?action=verAtracao&id=<%= atracao.getId()%>')">
                    <div class="imgTxtContainer">
                        <img class="imgAtracao img-fluid" src="<%= request.getContextPath() %>/static/img/atracao1.jpg" alt="Imagem Atraçãoo">
                        <p class="txtAtracao"><%= atracao.getTitulo() %></p>
                    </div>
                    <figcaption class="captionAtracao"><%= atracao.getDescricao() %></figcaption>
                </figure>
            <%  }
            } %>
        </div>
    </section>
</body>
</html>