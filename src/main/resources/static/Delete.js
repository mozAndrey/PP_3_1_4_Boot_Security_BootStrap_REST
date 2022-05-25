const urlDelete = 'http://localhost:8080/admin/api/delete/'
const deleteUserModal = document.querySelector('#modalDelete')
let currentUserId = null;




deleteUserModal.addEventListener('submit', (e) => {
    console.log(e.target.parentNode.parentNode)
    e.preventDefault();
    e.stopPropagation();
    fetch(urlDelete + currentUserId, {
        method: 'DELETE'
    })
        .then()

    $("#modalDelete").modal("hide")
    refreshTable()
})

on(document, 'click', '#delete-user', e => {
    const fila2 = e.target.parentNode.parentNode
    currentUserId = fila2.children[0].innerHTML

    document.getElementById('idDel').value = fila2.children[0].innerHTML
    document.getElementById('usernameDel').value = fila2.children[1].innerHTML
    document.getElementById('nameDel').value = fila2.children[2].innerHTML
    document.getElementById('lastnameDel').value = fila2.children[3].innerHTML
    document.getElementById('ageDel').value = fila2.children[4].innerHTML
    document.getElementById('emailDel').value = fila2.children[5].innerHTML
    document.getElementById('rolesDel').value = fila2.children[6].innerHTML
    $("#modalDelete").modal("show")
})
refreshTable()