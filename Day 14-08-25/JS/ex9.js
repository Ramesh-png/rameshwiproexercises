 const numbers = [1, 2, 3];

    const sum = (a, b, c) => a + b + c;

    const total = sum(...numbers);

    document.getElementById("result9").textContent = total;