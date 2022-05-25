const urlUpdate = 'http://localhost:8080/admin/api/update'
const editUserModal = document.querySelector('#editModal')



on(document, 'click', '#edit-user', el => {
    const userInfo = el.target.parentNode.parentNode
    document.querySelector('#idUpd').value = userInfo.children[0].innerHTML
    document.getElementById('usernameUpd').value = userInfo.children[1].innerHTML
    document.getElementById('nameUpd').value = userInfo.children[2].innerHTML
    document.getElementById('lastnameUpd').value = userInfo.children[3].innerHTML
    document.getElementById('ageUpd').value = userInfo.children[4].innerHTML
    document.getElementById('emailUpd').value = userInfo.children[5].innerHTML
    document.getElementById('passwordUpd').value = userInfo.children[6].innerHTML
    document.getElementById('editUser').value = userInfo.children[7].innerHTML
    $('#editModal').modal('show')
})


editUserModal.addEventListener('submit', el => {
    el.preventDefault();
    fetch(urlUpdate, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('idUpd').value,
            username: document.getElementById('usernameUpd').value,
            name: document.getElementById('nameUpd').value,
            lastname: document.getElementById('lastnameUpd').value,
            email: document.getElementById('emailUpd').value,
            age: document.getElementById('ageUpd').value,
            password: document.getElementById('passwordUpd').value,
            roles: [
                document.getElementById('editUser').value
            ]
        })
    }).then()

    $('#editModal').modal('hide')

    refreshTable()
})
