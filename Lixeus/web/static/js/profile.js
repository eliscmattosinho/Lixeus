/*
 * Author: Elis Mattosinho
 * Descrição: Atualização e gerenciamento do perfil do usuário, navegação para visualização e edição, e retorno para a página inicial do site.
 */

// PERFIL
document.getElementById("btnProfile").addEventListener("click", function (event) {
    event.preventDefault();

    window.location.href = "/Lixeus/LoaderServlet?action=verPerfil";
});

// Atualização do perfil
document.addEventListener("DOMContentLoaded", function () {
    const updateForm = document.getElementById("updateForm");

    updateForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(updateForm);

        fetch("/cliente", {
            method: 'POST',
            body: formData
        })
        .then(data => {
            if (data.trim() === 'success') {
                alert("Perfil atualizado com sucesso!");
                updateOriginalValues();
            } else {
                alert("Erro ao atualizar o perfil.");
            }
        })
        .catch(error => {
            console.error('Erro ao atualizar perfil:', error);
            alert("Erro ao atualizar o perfil.");
        });
    });

    // Função para atualizar os valores originais após a atualização bem-sucedida
    function updateOriginalValues() {
        originalValues.nome = document.getElementById("nome").value;
        originalValues.email = document.getElementById("email").value;
        originalValues.cpf = document.getElementById("cpf").value;
        originalValues.tel = document.getElementById("tel").value;
        originalValues.data_nasc = document.getElementById("data_nasc").value;
    }

    // Valores originais carregados
    let originalValues = {
        nome: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        cpf: document.getElementById("cpf").value,
        tel: document.getElementById("tel").value,
        data_nasc: document.getElementById("data_nasc").value,
    };
});

// Voltar para index.jsp
document.getElementById("backButton").addEventListener("click", function (event) {
    event.preventDefault();

    window.location.href = "/Lixeus/index.jsp";
});