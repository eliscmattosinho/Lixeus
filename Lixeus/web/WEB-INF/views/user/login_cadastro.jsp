<!-- Author: Elis Mattosinho
    Descrição: View dos forms de login e cadastro. -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Formulários</title>
    <!-- Font Awesome -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />

    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/global.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/login_cadastro.css"
    />
  </head>

  <body>
    <div id="loginForm" class="formCliente" style="display: none">
      <div class="blockCliente d-flex justify-content-around align-items-center">
        <div>
          <h2>Login</h2>
          <form
            class="d-flex flex-column justify-content-center align-items-start"
            action="login"
            method="post"
          >
            <label class="labelLogin" for="loginEmail">E-mail:</label>
            <input
              type="email"
              id="loginEmail"
              name="loginEmail"
              placeholder="example@gmail.com"
              required
            /><br />
            <label class="labelLogin" for="loginSenha">Senha:</label>
            <div class="password-wrapper">
              <input
                type="password"
                id="loginSenha"
                name="loginSenha"
                placeholder="******"
                required
              />
              <i class="fa fa-eye toggle-password"></i>
            </div>
            <br />
            <button type="submit">Entrar</button>
          </form>
          <a href="#cadastroForm">Cadastre-se</a>
        </div>
        <div>
          <img src="<%= request.getContextPath() %>/static/img/lixeus-icon.png" width="300" />
        </div>
      </div>
    </div>

    <% String loginError=request.getParameter("loginError"); if
    ("senha".equals(loginError)) { %>
    <script>
      alert("Senha ou email incorretos.");
      window.location.href = "index.jsp#loginForm";
    </script>
    <% } else if ("email".equals(loginError)) { %>
    <script>
      alert("Email não encontrado em nossa base de dados.");
      // Redirecionamento para o index.jsp com o formulário de cadastro
      window.location.href = "index.jsp#cadastroForm";
    </script>
    <% } %>

    <div id="cadastroForm" class="formCliente" style="display: none">
      <h2>Cadastro</h2>
      <div class="blockCad d-flex justify-content-around align-items-center">
        <div>
          <form
            class="d-flex flex-column justify-content-center align-items-start"
            action="cadastro"
            method="post"
          >
            <label class="labelCad" for="cadastroNome">Nome:</label>
            <input
              type="text"
              id="cadastroNome"
              name="cadastroNome"
              placeholder="Nome Sobrenome"
              required
            /><br />
            <label class="labelCad" for="cadastroEmail">E-mail:</label>
            <input
              type="email"
              id="cadastroEmail"
              name="cadastroEmail"
              placeholder="example@gmail.com"
              required
            /><br />
            <label class="labelCad" for="cadastroSenha">Senha:</label>
            <div class="password-wrapper">
              <input
                type="password"
                id="cadastroSenha"
                name="cadastroSenha"
                placeholder="******"
                required
              />
              <i class="fa fa-eye toggle-password"></i>
            </div>
            <br />
            <label class="labelCad" for="cadastroCPF">CPF:</label>
            <input
              type="text"
              id="cadastroCPF"
              name="cadastroCPF"
              placeholder="000.000.000-00"
              required
            /><br />
            <label class="labelCad" for="cadastroTel">Telefone:</label>
            <input
              type="text"
              id="cadastroTel"
              name="cadastroTel"
              placeholder="(00) 000000000"
              required
            /><br />
            <label class="labelCad" for="cadastroDataNasc"
              >Data de Nascimento:</label
            >
            <input
              type="date"
              id="cadastroDataNasc"
              name="cadastroDataNasc"
              placeholder="MM-DD-YYYY"
              required
            /><br />
            <button type="submit">Cadastrar</button>
          </form>
          <p>Já possui conta? <a href="#loginForm">Faça login.</a></p>
        </div>
        <div>
          <img src="<%= request.getContextPath() %>/static/img/lixeus-icon.png" width="500" />
        </div>
      </div>
    </div>

    <% if (request.getAttribute("cadastroSuccess") !=null) { %>
    <div id="cadastroSuccess">
      <p><%= request.getAttribute("cadastroSuccess") %></p>
    </div>
    <% } %> <% if (request.getAttribute("loginError") !=null) { %>
    <div id="loginError">
      <p><%= request.getAttribute("loginError") %></p>
    </div>
    <% } %>

    <script
      type="text/javascript"
      src="<%= request.getContextPath() %>/static/js/login_cadastro.js"
    ></script>
  </body>
</html>
