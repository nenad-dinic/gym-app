function filterUsers() {{
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
}}