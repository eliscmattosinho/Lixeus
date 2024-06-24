<!--
    Author: Matheus Maqueda
    View Atração
-->

<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Lixeus</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Bootstrap -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <!-- CSS -->
    <link rel="stylesheet" href="static/css/global.css" />
    <link rel="stylesheet" href="static/css/index.css" />
  </head>
  <body>
    <header>
      <!-- Nav -->
      <nav class="navbar navbar-expand-lg" data-bs-theme="dark">
        <div class="container-fluid justify-content-center">
          <a
            id="logoNav"
            class="navbar-brand text-center"
            href="<%= request.getContextPath() %>/index.jsp"
            ><img src="static/img/lixeus-logo.png" alt="LxUs" width="110px"
          /></a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div
            id="contentNav"
            class="collapse navbar-collapse justify-content-center"
            id="navbarSupportedContent"
          >
            <ul class="navbar-nav mb-2 mb-lg-0 justify-content-between w-100">
              <li class="nav-item">
                <a class="nav-link" href="#historia">Artigos & Pesquisas</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#atracoes">Atrações</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#atracoes">Horários & Valores</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#footer">FAQ</a>
              </li>

              <!-- Author: Elis Mattosinho -->
              <%-- Verificar se há uma sessão ativa com o atributo "cliente"
              --%>
              <c:choose>
                <c:when test="${not empty sessionScope.cliente}">
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Conta
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown" style="width: max-content;">
                      <li><a id="btnProfile" class="dropdown-item" href="#">Perfil</a></li>
                      <li><a class="dropdown-item" href="/Lixeus/LoaderServlet?action=verCompras">Minhas compras</a></li>
                    </ul>
                  </li>        
                </c:when>
                <c:otherwise>
                  <li class="nav-item">
                    <a
                      class="nav-link"
                      href="/Lixeus/LoaderServlet?action=loginCadastro"
                    >
                      <button type="button" id="btnNav">Login/Cadastro</button>
                    </a>
                  </li>
                </c:otherwise>
              </c:choose>
              <!-- Author: Elis Mattosinho -->
            </ul>
          </div>
        </div>
      </nav>
    </header>

    <main>
      <!-- Inserção dinâmica: index.js  -->
    </main>

    <!-- Bootstrap -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.6/jquery.inputmask.min.js"></script>
    <script type="text/javascript" src="static/js/index.js"></script>
    <script type="text/javascript" src="static/js/profile.js"></script>
    <script type="text/javascript" src="static/js/login_cadastro.js"></script>
  </body>
</html>
