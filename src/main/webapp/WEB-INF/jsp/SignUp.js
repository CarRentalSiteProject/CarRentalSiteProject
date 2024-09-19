function validateForm(event) {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        document.getElementById("password").value = "";
        document.getElementById("confirmPassword").value = "";
        event.preventDefault(); // Prevent form submission
    }
}