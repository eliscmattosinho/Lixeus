<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Minhas Compras</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/returns.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/ticket.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css"/>
</head>
<body>
    <div class="btnReturnBlock">
        <a class="btnReturnOne" href="#" onclick="history.back(); return false;">Voltar</a>
    </div>
    <h2>Ticket(s) de ${atracoes[0].titulo}</h2>

    <div id="ticketsBlock">
        <c:forEach var="atracao" items="${atracoes}" varStatus="outerLoop">
            <div class="ticketModel">
                <img class="ticketLogo" src="${pageContext.request.contextPath}/static/img/lixeus-logo2.png" width="80" />
                <div class="ticketInfos">
                    <p class="ticketTitle">${atracao.titulo}</p>
                    <p class="ticketDate">${atracao.data}</p>
                    <p class="ticketHour">Hora: ${atracao.hora_ini} - ${atracao.hora_fim}</p>
                    <div class="codeBlock">
                        <p class="ticketacessCode">${tickets[outerLoop.index].accessCode}</p>
                    </div>
                </div>
            </div>
        </c:forEach>   
    </div>
    <button id="btnTicketPrint" onclick="printWithBackgrounds()">Imprimir</button>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ticket.js"></script>
</body>
</html>
