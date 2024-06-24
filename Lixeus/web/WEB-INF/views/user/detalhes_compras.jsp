<!-- Author: Elis Mattosinho
    Descrição: View da página de Minhas Compras - registro de compras -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Minhas Compras</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/returns.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/detalhes_compras.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css"/>
</head>
<body>
    <div class="blockD">
        <div class="btnReturnBlock">
            <a class="btnReturnOne" href="#" onclick="history.back(); return false;">Voltar</a>
        </div>

        <h2>Minhas Compras</h2>

        <table border="1">
            <thead>
                <tr>
                    <th>ID da Compra</th>
                    <th>Data da Compra</th>
                    <th>Forma de Pagamento</th>
                    <th>Quantidade de Tickets</th>
                    <th>Status da Compra</th>
                    <th>Total</th>
                    <th>Detalhes</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="compra" items="${comprasDoUsuario}">
                    <tr>
                        <td>${compra.idCompra}</td>
                        <td>${compra.dataCompra}</td>
                        <td>${compra.formaPagamento}</td>
                        <td>${compra.quantidadeTickets}</td>
                        <td>${compra.statusCompra}</td>
                        <td>R$ ${compra.total}</td>
                        <td><a href="/Lixeus/LoaderServlet?action=verCompra&id=${compra.idCompra}">Detalhes</a></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty comprasDoUsuario}">
                    <tr>
                        <td colspan="7">Nenhuma compra encontrada.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>
