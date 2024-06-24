document.addEventListener('DOMContentLoaded', fetchTickets);

function fetchTickets() {
    fetch('/Lixeus/LoaderServlet?action=verTicket')
        .then(response => response.json())
        .then(data => {
            console.log('Dados recebidos:', data);

            const ticketsBlock = document.getElementById('ticketsBlock');
            ticketsBlock.innerHTML = '';

            data.forEach(ticket => {
                const ticketHTML = `
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
                `;
                ticketsBlock.innerHTML += ticketHTML;
            });
        })
        .catch(error => console.error('Erro ao buscar tickets:', error));
}

function printWithBackgrounds() {
    const printStyles = `
        #ticketsBlock {
            display: flex !important;
            flex-direction: row !important;
            justify-content: center !important;
            align-items: center !important;
            flex-wrap: wrap !important;
        }
        .ticketModel {
            background: linear-gradient(to bottom, #011632, #560743) !important;
            margin: 30px !important;
            padding: 20px !important;
            border: 1px solid #fff !important;
            border-radius: 10px !important;
            width: 250px !important;
            max-height: 500px !important;
        }
        .ticketTitle,
        .ticketDate,
        .ticketHour,
        .ticketaccessCode  {
            color: white !important;
            font-size: 14px !important;
        }
    `;

    const styleSheet = document.createElement('style');
    styleSheet.media = 'print';
    styleSheet.innerText = printStyles;
    document.head.appendChild(styleSheet);

    window.print();
}
