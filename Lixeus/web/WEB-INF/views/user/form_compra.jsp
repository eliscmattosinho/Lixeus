<!-- Author: Elis Mattosinho
    Descrição: View da página de Compra -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.Lixeus.model.Atracao" %>
<!DOCTYPE html>
<html>
<head>
    <title>Compra de Tickets</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/returns.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/compra.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css"/>
</head>
<body>

    <div class="btnReturnBlock">
        <a class="btnReturnOne" href="#" onclick="history.back(); return false;">Voltar</a>
    </div>
    <h2>Atração: <%= ((Atracao) request.getAttribute("atracao")).getTitulo() %></h2>
    <form id="formCompra" action="compra" method="post">
        <div class="optBlock">
            <label for="data_compra">Data da Compra:</label>
            <input type="text" id="data_compra" name="data_compra" value="<%= java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy")) %>" readonly disabled>
        </div>

        <div class="optBlock">
            <label for="forma_pag">Forma de Pagamento:</label>
            <select id="forma_pag" name="forma_pag" required>
                <option value="PIX">PIX</option>
                <option value="Boleto">Boleto</option>
            </select>
        </div>

        <div class="optBlock">
            <label for="qtd_ticket">Quantidade de Tickets:</label>
            <input type="number" id="qtd_ticket"
                placeholder="Max: <%= ((Atracao) request.getAttribute("atracao")).getMax_ticket() %>"
            name="qtd_ticket" required>
        </div>

        <div class="optBlock"><input type="hidden" id="atracaoId" name="atracaoId" value="<%= ((Atracao) request.getAttribute("atracao")).getId() %>"></div>
        
        <div class="optBlock"><input id="btnComprar" type="submit" value="Comprar"></div>
    </form>
</body>
</html>
