async function deleteH(id) {
    let response = await fetch("http://localhost:8080/api/hall?id=" + id, {
        method: "DELETE"
    });
    if (response != null) {
        window.location.reload();
    }
}

function update(id) {
    window.location.href = "/hall?id=" + id;

}