<!-- Author: Elis Mattosinho
    Descrição: View da página de perfil do usuário -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Perfil do Usuário</title>
    <!-- Bootstrap -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />

    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/profile.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/global.css"
    />
  </head>
  <body>
    <div id="profileBlock">
      <h2>Perfil do Usuário</h2>
      <form id="updateForm" action="cliente" method="post">
        <input type="hidden" name="action" value="updateProfile" />
        <input type="hidden" name="id_cliente" value="${cliente.id_cliente}" />
        <div class="profileElement">
          <label for="nome">Nome:</label>
          <input
            type="text"
            id="nome"
            name="nome"
            value="${cliente.nome}"
            required
          /><br />
        </div>
        <div class="profileElement">
          <label for="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            value="${cliente.email}"
            required
          /><br />
        </div>
        <div class="profileElement">
          <label for="cpf">CPF:</label>
          <input
            type="text"
            id="cpf"
            name="cpf"
            value="${cliente.cpf}"
            required
          /><br />
        </div>
        <div class="profileElement">
          <label for="tel">Telefone:</label>
          <input
            type="text"
            id="tel"
            name="tel"
            value="${cliente.tel}"
            required
          /><br />
        </div>
        <div class="profileElement">
          <label for="data_nasc">Data de Nascimento:</label>
          <input
            type="date"
            id="data_nasc"
            name="data_nasc"
            value="${cliente.data_nasc}"
            required
          /><br />
        </div>
        <div class="profileElement">
          <label for="senha">Nova Senha:</label>
          <input id="senha" type="password" name="senha" /><br />
        </div>
        <div class="profileElement">
          <button class="btnUpdate" type="submit">Atualizar</button>
        </div>
      </form>
      <div class="d-flex justify-content-around">
        <form class="btnReturn" action="index.jsp">
          <button id="backButton" type="submit">Voltar</button>
        </form>
        <form
          class="btnDelete"
          action="cliente"
          method="post"
          onsubmit="return confirm('Tem certeza que deseja deletar sua conta?');"
        >
          <input type="hidden" name="action" value="delete" />
          <input
            type="hidden"
            name="id_cliente"
            value="${cliente.id_cliente}"
          />
          <button type="submit">Deletar Conta</button>
        </form>
        <form class="btnLogout" action="cliente" method="post">
          <input type="hidden" name="action" value="logout" />
          <button type="submit">Logout</button>
        </form>
      </div>
    </div>

    <script
      type="text/javascript"
      src="${pageContext.request.contextPath}/static/js/profile.js"
    ></script>
  </body>
</html>
