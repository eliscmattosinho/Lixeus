// function carregarComprasUsuario(clienteId) {
//     fetch(`/compra?action=verCompras&cliente_id=${clienteId}`)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Erro ao carregar compras');
//             }
//             return response.json();
//         })
//         .then(data => {
//             exibirCompras(data);
//         })
//         .catch(error => {
//             console.error('Erro ao buscar compras:', error);
//         });
// }

// function exibirCompras(compras) {
//     let tabelaCompras = document.querySelector('#tabelaCompras tbody');
//     tabelaCompras.innerHTML = '';

//     compras.forEach(compra => {
//         let row = `
//             <tr>
//                 <td>${compra.idcompra}</td>
//                 <td>${formatarData(compra.data_compra)}</td>
//                 <td>${compra.forma_pag}</td>
//                 <td>${compra.qtd_ticket}</td>
//                 <td>${compra.status_compra}</td>
//                 <td>R$ ${compra.total.toFixed(2)}</td>
//                 <td><a href="compra?action=verCompra&id=${compra.idcompra}">Detalhes</a></td>
//             </tr>
//         `;
//         tabelaCompras.innerHTML += row;
//     });
// }
