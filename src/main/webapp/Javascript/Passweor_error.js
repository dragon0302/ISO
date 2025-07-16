document.getElementById("Password").addEventListener("input", function () {
    const password = this.value;
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
    const msg = document.getElementById("password-feedback");

    if (!regex.test(password)) {
        msg.textContent = "La password non è abbastanza sicura.";
        msg.style.color = "red";
    } else {
        msg.textContent = "Password valida.";
        msg.style.color = "green";
    }
});
