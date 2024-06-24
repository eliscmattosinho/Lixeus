/*
* Author: Matheus Maqueda
* Arquivo contendo manipulação de admin
*/

function showForm(formId) {
    const forms = document.querySelectorAll('.formAtracao');
    forms.forEach(form => form.classList.remove('active'));

    const formToShow = document.getElementById(`form${formId}`);
    if (formToShow) {
        formToShow.classList.add('active');
    }
}

function enableEdit(id, contextPath) {
    // pega cada elemento da linha que se refere ao id
    const row = document.getElementById(`row-${id}`);
    const titulo = document.getElementById(`titulo-${id}`);
    const descricao = document.getElementById(`descricao-${id}`);
    const preco = document.getElementById(`preco-${id}`);
    const data = document.getElementById(`data-${id}`);
    const horaIni = document.getElementById(`horaIni-${id}`);
    const horaFim = document.getElementById(`horaFim-${id}`);
    const maxTicket = document.getElementById(`maxTicket-${id}`);

    // transforma os elementos em input
    titulo.innerHTML = `<textarea type="text" class="colTitulo" id="editTitulo-${id}">${titulo.innerText}</textarea>`;
    descricao.innerHTML = `<textarea type="text" oninput="charCounter(this)" class="colDescricao" id="editDescricao-${id}">${descricao.getAttribute('data-descricao')}</textarea>`;
    preco.innerHTML = `<input type="number" class="colPreco" step="0.01" value="${parseFloat(preco.innerText.replace(/[^0-9,.]/g, '').replace(',', '.'))}" id="editPreco-${id}">`;
    data.innerHTML = `<input type="date" class="colData" value="${data.innerText}" id="editData-${id}">`;
    horaIni.innerHTML = `<input type="time" class="colHoraIni" value="${horaIni.innerText}" id="editHoraIni-${id}">`;
    horaFim.innerHTML = `<input type="time" class="colHoraFim" value="${horaFim.innerText}" id="editHoraFim-${id}">`;
    maxTicket.innerHTML = `<input type="number" class="colMaxTicket" value="${maxTicket.innerText}" id="editMaxTicket-${id}">`;

    // limpa botões atuais
    row.querySelector('.bi-pencil-square').style.display = 'none';
    row.querySelector('.bi-trash3-fill').style.display = 'none';

    // adiciona botões de submit e cancelar no ultimo elemento td da row
    const actionsCell = row.querySelector('td:last-child');
    actionsCell.innerHTML += `
        <button onclick="submitEdit(${id}, '${contextPath}')" class="btnTable bi bi-check-circle-fill"></button>
        <button onclick="cancelEdit('${contextPath}')" class="btnTable mb-1 bi bi-x-circle-fill"></button>
    `;
}

// filtrar o tamanho da descrição para 300 caracteres
function charCounter(txtElement) {
    var txtLength = txtElement.value.length;

    // Verifica se o comprimento excede 300 caracteres
    if (txtLength > 300) {
        // Exibe uma mensagem de aviso para o usuário
        alert("A descrição não pode ter mais de 300 caracteres.");

        // Limita o comprimento do texto para 300 caracteres
        txtElement.value = txtElement.value.substring(0, 300);
    }
};

function cancelEdit(contextPath) {
    // recarrega a página para cancelar edição
    location.href = contextPath + "/admin/atracao?cancel=1";
}

function submitEdit(id, contextPath) {
    // pega todos os elementos, mas dessa vez os seus valores que foram editados
    const editTitulo = document.getElementById(`editTitulo-${id}`).value;
    const editDescricao = document.getElementById(`editDescricao-${id}`).value;
    const editPreco = document.getElementById(`editPreco-${id}`).value;
    const editData = document.getElementById(`editData-${id}`).value;
    const editHoraIni = document.getElementById(`editHoraIni-${id}`).value;
    const editHoraFim = document.getElementById(`editHoraFim-${id}`).value;
    const editMaxTicket = document.getElementById(`editMaxTicket-${id}`).value;

    // cria um elemento form e modifica seus atributos
    const form = document.createElement('form');
    form.method = 'post';
    form.action = `${contextPath}/admin/atracao?action=update&id=${id}`;
    form.acceptCharset = 'UTF-8';

    // adiciona os inputs no form através de HTML
    form.innerHTML = `
        <input type="hidden" name="editTitulo" value="${editTitulo}">
        <input type="hidden" name="editDescricao" value="${editDescricao}">
        <input type="hidden" name="editPreco" value="${editPreco}">
        <input type="hidden" name="editData" value="${editData}">
        <input type="hidden" name="editHoraIni" value="${editHoraIni}">
        <input type="hidden" name="editHoraFim" value="${editHoraFim}">
        <input type="hidden" name="editMaxTicket" value="${editMaxTicket}">
    `;

    // adiciona o form ao body e envia-o
    document.body.appendChild(form);
    form.submit();
}

function confirmDelete(id, contextPath) {
    if (window.confirm('Você está certo de que deseja deletar?')) {
        location.href = contextPath + "/admin/atracao?action=delete&id=" + id;
    }
}

function showDesc(descricao) {
    window.alert(descricao);
}