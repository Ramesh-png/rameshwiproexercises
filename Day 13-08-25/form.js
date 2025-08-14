    function validateEmailField() {
        const email = document.getElementById("email").value.trim();
        const emailError = document.getElementById("emailError");
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (email === "") {
            emailError.textContent = "Email cannot be empty";
            return false;
        } else if (!emailPattern.test(email)) {
            emailError.textContent = "Invalid email format";
            return false;
        } else {
            emailError.textContent = "";
            return true;
        }
    }

    function validatePasswordField() {
        const password = document.getElementById("password").value.trim();
        const passwordError = document.getElementById("passwordError");

        if (password === "") {
            passwordError.textContent = "Password cannot be empty";
            return false;
        } else {
            passwordError.textContent = "";
            return true;
        }
    }

    function validateForm() {
        const isEmailValid = validateEmailField();
        const isPasswordValid = validatePasswordField();

        if (isEmailValid && isPasswordValid) {
            alert("Login successful!");
        }
    }

    document.getElementById("email").addEventListener("blur", validateEmailField);
    document.getElementById("password").addEventListener("blur", validatePasswordField);