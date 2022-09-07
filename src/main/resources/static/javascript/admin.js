function filterUsers() {
    let username = document.getElementById("username").value;
    let role = document.getElementById("role").value;
    let rows = document.getElementById("userTable").getElementsByTagName("tr");

    for (let i = 1; i < rows.length; i++) {
        let usernameC = rows[i].getElementsByTagName("td")[0];
        let roleC = rows[i].getElementsByTagName("td")[1];

        if (!usernameC.innerText.toLowerCase().includes(username.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        } if (!roleC.innerText.toLowerCase().includes(role.toLowerCase())) {
            rows[i].style.display = "none";
            continue;
        }
        rows[i].style.display = "";
    }
}

async function acceptC(id) {
    let response = await fetch("/admin/request/accept?id=" + id, {
        method: "post"
    })
    window.location.reload();

}

async function declineC(id) {
    let response = await fetch("/admin/request/decline?id=" + id, {
        method: "post"
    })
    window.location.reload();
}

async function deleteH(id) {
    let response = await fetch("/admin/hall/delete?id=" + id, {
        method: "post"
    });
    window.location.reload();

}

async function acceptComment(id) {
    let response = await fetch("/admin/comment/accept?id=" + id, {
        method: "post"
    })
    window.location.reload();
}

async function declineComment(id) {
    let response = await fetch("/admin/comment/decline?id=" + id, {
        method: "post"
    })
    window.location.reload();
}

function updateH(id) {
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

function calcSum() {
    let rows = document.getElementById("reportTable").getElementsByTagName("tr");
    let priceSum = 0;
    let count = 0;
    for (let i = 1; i < rows.length - 1; i++) {
        let priceC = rows[i].getElementsByTagName("td")[3];
        let countC = rows[i].getElementsByTagName("td")[2];

        priceSum += parseInt(priceC.innerText);
        count += parseInt(countC.innerText);
    }
    rows[rows.length - 1].getElementsByTagName("td")[2].innerText = count;
    rows[rows.length - 1].getElementsByTagName("td")[3].innerText = priceSum;
}

calcSum();