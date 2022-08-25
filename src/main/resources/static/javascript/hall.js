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

function filterHalls() {
    let name = document.getElementById("hallTag").value;
    let rows = document.getElementById("hallTable").getElementsByTagName("tr");

    for (let i = 1; i < rows.length; i++) {
        let hallTagC = rows[i].getElementsByTagName("td")[0];

        if (!hallTagC?.innerText.toLowerCase().includes(name.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        }
        rows[i].style.display = "";
    }
}