window.onload = function() {
    const userData = localStorage.getItem("userId");
    if (userData) {
        document.getElementById("status").innerText = `Logged in as: ${userData}`;
    } else {
        document.getElementById("status").innerText = "Not logged in";
    }
}

function login() {
    const userId = document.getElementById("userId").value;
    const password = document.getElementById("password").value;

    if (userId && password) {
        localStorage.setItem("userId", userId);
        document.getElementById("status").innerText = `Logged in as: ${userId}`;
        alert("Login successful!");
    } else {
        alert("Please enter both User ID and Password.");
    }
}

function logout() {
    localStorage.removeItem("userId");
    document.getElementById("status").innerText = "Not logged in";
    alert("Logged out successfully!");
}
