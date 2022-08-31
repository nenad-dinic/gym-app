function checkPasswords() {
    let passwordField = document.getElementById("password");
    let repeatPasswordField = document.getElementById("repeatPassword");
    let errorMsg = document.getElementById("errorMsg");
    let submit = document.getElementById("submit");

    if (passwordField.value != repeatPasswordField.value) {
        submit.disabled = true;
        errorMsg.style.display = "block";
    } else {
        submit.disabled = false;
        errorMsg.style.display = "none";
    }
}
checkPasswords();