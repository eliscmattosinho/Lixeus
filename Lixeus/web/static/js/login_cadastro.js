/*
 * Author: Elis Mattosinho
 * Descrição: JS que utiliza eventos para carregar
 * dinamicamente o conteúdo do formulário de login/cadastro através de uma requisição fetch para o
 * LoaderServlet e faz troca entre form de login e cadastro.
 */

document.addEventListener('DOMContentLoaded', function () {

    // LOGIN E CADASTRO
    document.getElementById("btnNav").addEventListener("click", function (event) {
        event.preventDefault();

        fetch("/Lixeus/LoaderServlet?action=loginCadastro")
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro conexão ' + response.statusText);
                }
                return response.text();
            })
            .then(data => {
                document.querySelector("main").innerHTML = data;
                setupForms();
                exibirAlertaCadastro();
            })
            .catch(error => console.error('Erro ao carregar o formulário de login e cadastro:', error));
    });

    function setupForms() {
        const loginForm = document.getElementById("loginForm");
        const cadastroForm = document.getElementById("cadastroForm");

        if (loginForm && cadastroForm) {
            document.querySelector("a[href='#cadastroForm']").addEventListener("click", function (e) {
                e.preventDefault();
                showForm('cadastroForm');
            });

            document.querySelector("a[href='#loginForm']").addEventListener("click", function (e) {
                e.preventDefault();
                showForm('loginForm');
            });

            showForm('loginForm');
        } else {
            console.error('Erro ao configurar os formulários: Elementos não encontrados');
        }
    }

    function showForm(formId) {
        const forms = document.querySelectorAll("div[id$='Form']");
        forms.forEach(form => form.style.display = "none");

        const formToShow = document.getElementById(formId);
        if (formToShow) {
            formToShow.style.display = "block";
        }
    }

    function exibirAlertaCadastro() {
        const mensagemElement = document.querySelector("#cadastroSuccess p");
        if (mensagemElement && mensagemElement.textContent.trim() !== "") {
            alert(mensagemElement.textContent);
        }
    }

    initPasswordToggle();

    $("#cadastroCPF").inputmask("999.999.999-99");
    $("#cadastroTel").inputmask("(99) 99999-9999");

    document.querySelectorAll('form').forEach(function (form) {
        form.addEventListener('submit', function (e) {
            let isValid = true;
            this.querySelectorAll('input').forEach(function (input) {
                let value = input.value.trim();
                input.value = value;  // Trim spaces

                if (input.type === "email") {
                    isValid = validateEmail(value) && isValid;
                } else if (input.type === "text" && input.name === "cadastroNome") {
                    isValid = validateNome(value) && isValid;
                } else if (input.type === "text" && input.name === "cadastroCPF") {
                    isValid = validateCPF(value) && isValid;
                } else if (input.type === "text" && input.name === "cadastroTel") {
                    isValid = validateTelefone(value) && isValid;
                }
            });

            if (!isValid) {
                e.preventDefault();
                alert("Por favor, corrija os campos com erros.");
            }
        });
    });

    function validateEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }

    function validateNome(nome) {
        const nomePattern = /^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/;
        return nomePattern.test(nome);
    }

    function validateCPF(cpf) {
        cpf = cpf.replace(/[^\d]/g, "");
        if (cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) return false;
        let soma = 0, resto;

        for (let i = 1; i <= 9; i++) soma += parseInt(cpf.substring(i - 1, i)) * (11 - i);
        resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        if (resto !== parseInt(cpf.substring(9, 10))) return false;

        soma = 0;
        for (let i = 1; i <= 10; i++) soma += parseInt(cpf.substring(i - 1, i)) * (12 - i);
        resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        if (resto !== parseInt(cpf.substring(10, 11))) return false;

        return true;
    }

    function validateTelefone(tel) {
        const telPattern = /^\(\d{2}\) \d{5}-\d{4}$/;
        return telPattern.test(tel);
    }
});

function initPasswordToggle() {
    let togglePasswordIcons = document.querySelectorAll('.toggle-password');

    togglePasswordIcons.forEach(function (icon) {
        icon.addEventListener('click', function () {
            var passwordField = icon.parentElement.querySelector('input[type="password"]');

            if (passwordField) {
                if (passwordField.type === 'password') {
                    passwordField.type = 'text';
                    icon.classList.remove('fa-eye-slash');
                    icon.classList.add('fa-eye');
                } else {
                    passwordField.type = 'password';
                    icon.classList.remove('fa-eye');
                    icon.classList.add('fa-eye-slash');
                }
            }
        });
    });
}

    