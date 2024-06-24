<!-- Author: Elis Mattosinho
    Descrição: View da página de Minhas Compras(detalhes para dados de atração e ticket(s)) -->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalhes da Compra</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/returns.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/detalhes_compras.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
</head>
<body>
    <div class="blockD">
        <div class="btnReturnBlock">
            <a class="btnReturnOne" href="#" onclick="history.back(); return false;">Voltar</a>
        </div>

        <h2>Minha Compra: ${compra.idCompra}</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>ID da Atração</th>
                    <th>Título da Atração</th>
                    <th>Descrição</th>
                    <th class="colData">Data</th>
                    <th>Ticket</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="atracao" items="${atracoes}">
                    <tr>
                        <td>${atracao.id}</td>
                        <td>${atracao.titulo}</td>
                        <td>${atracao.descricao}</td>
                        <td>${atracao.data}</td>
                        <td>
                            <a href="/Lixeus/LoaderServlet?action=verTicket&id=${compra.idCompra}">Ver tickets</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>            
        </table>
    </div>
</body>
</html>
