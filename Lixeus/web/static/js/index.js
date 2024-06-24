/*
 * Author: Elis Mattosinho
 * Descrição: O código JavaScript carrega dinamicamente conteúdo para a página principal e outras páginas através de requisições fetch.
 */

document.addEventListener("DOMContentLoaded", function () {

    // Carregar o conteúdo inicial da landing page
    fetch("/Lixeus/LoaderServlet?action=landingPage")
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro conexão ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            document.querySelector("main").innerHTML = data;
        })
        .catch(error => console.error('Erro ao carregar a landing page:', error));
});

function loadPage(url) {
    fetch("/Lixeus/" + url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro conexão ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            document.querySelector("main").innerHTML = data;
        })
        .catch(error => console.error('Erro ao carregar a página:', error))
}

