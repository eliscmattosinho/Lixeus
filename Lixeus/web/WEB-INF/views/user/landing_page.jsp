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
    <section class="container parallax" id="sectionInicio">
        <div class="container" id="inicio">
            <h1 class="display">Lixeus</h1>
            <h3 class="mensagem">Bem-vindo(a) ao Planetário Lixeus!</h3>
            <h2 class="subtitle">Desbravamento além-Terra e conhecimento galáctico.</h2>
        </div>
    </section>

    <section id="historia">
        <div class="container-fluid historyContent">
            <h2 class="titulo tituloPreto">Nossa história</h2>
            <p class="texto"> Inaugurada em 15 de abril de 2004, a Fundação Lixeus se dedica a difundir Astronomia e Ciências afins gerando, em todos os públicos, uma experiência encantadora e inesquecível.</p>
            <div class="row cardB">
                <div class="bothCards col-md-6">
                    <figure class="cardHistory">
                        <img class="img-fluid" src="static/img/corporacao.jpg" alt="Sócios Corporativos">
                        <figcaption class="captionHistory">Sócios Colaborativos da SpaceZ</figcaption>
                    </figure>
                </div>
                <div class="bothCards col-md-6">
                    <figure class="cardHistory">
                        <img class="img-fluid" src="static/img/espaco.jpg" alt="Espaço">
                        <figcaption class="captionHistory">Conheça nosso espaço!</figcaption>
                    </figure>
                </div>
            </div>
        </div>
    </section>

    <section class="parallax" id="atracoes">
        <h2 class="titulo tituloBranco">Atrações</h2>
        <div class="cardsAtracoes">
        <%  List<Atracao> atracoes = (List<Atracao>)request.getAttribute("latestAtracoes");
            if (atracoes.isEmpty()) { %>
                <h3 class="text-light">Não há atrações no momento</h3>
            <% } else {
                for(Atracao atracao:atracoes) { %>
                <figure class="cardAtracao" onclick="loadPage('LoaderServlet?action=verAtracao&id=<%= atracao.getId()%>')">
                    <div class="imgTxtContainer">
                        <img class="imgAtracao img-fluid" src="<%= request.getContextPath() %>/static/img/atracao1.jpg" alt="Imagem AtraÃ§Ã£o">
                        <p class="txtAtracao"><%= atracao.getTitulo() %></p>
                    </div>
                    <figcaption class="captionAtracao"><%= atracao.getDescricao() %></figcaption>
                </figure>
            <%  }
            } %>
        </div>
        <button class="botao btnWhite" id="btnVerAtracoes" onclick="loadPage('LoaderServlet?action=verAtracoes')">Ver Mais</button>
    </section>

    <footer id="footer">
        <div class="row footerHeader">
            <div class="col-md-6 titleFooter">
                <h2 class="display">Lixeus</h2>
            </div>
            <div class="col-md-6 socialMedia">
                Siga-nos na web!<br>
                <span class="bi bi-instagram"></span>
                <span class="bi bi-linkedin"></span>
                <span class="bi bi-facebook"></span>
            </div>
        </div>
        <div class="row">
            <div class="notifyContainer">
                <h3 class="footerSubtitle">Receba notificações:</h3>
                <form>
                    <div>
                        <label for="emailNotify">Email*:</label>
                        <input type="email" id="emailNotify" name="emailNotify" placeholder="E-mail">
                    </div>

                    <div>
                        <label for="nameNotify">Nome:</label>
                        <input type="text" id="nameNotify" name="nameNotify" placeholder="Nome e Sobrenome">
                    </div>

                    <button class="botao btnBlack">Enviar</button>
                </form>
            </div>
        </div>
        <div class="row listsRow">
            <div class="col-md-3 w-auto">
                <h3 class="footerSubtitle">Sobre Nós</h3>
                <ul class="footerList">
                    <li>Fundação</li>
                    <li>Plano Estratégico</li>
                    <li>FAQ</li>
                </ul>
            </div>
            <div class="col-md-3 w-auto">
                <h3 class="footerSubtitle">Como Chegar?</h3>
                <ul class="footerList">
                    <li>Fundação</li>
                    <li>Plano Estratégico</li>
                    <li>FAQ</li>
                </ul>
            </div>
            <div class="col-md-3 w-auto">
                <h3 class="footerSubtitle">Sócios</h3>
                <ul class="footerList">
                    <li>SpaceZ</li>
                    <li>Planetários</li>
                    <li>AVM</li>
                </ul>
            </div>
        </div>
    </footer>
</body>
</html>