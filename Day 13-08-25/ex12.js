    const cities = ["Hyderabad", "Visakhapatnam", "Vijayawada", "Rajahmundry", "Tirupati", "Warangal", "Guntur"];

    function loadCities() {
        const sortedCities = cities.slice().sort();

        const dropdown = document.getElementById("cityDropdown");

        dropdown.length = 1;

        sortedCities.forEach(city => {
            const option = document.createElement("option");
            option.value = city;
            option.textContent = city;
            dropdown.appendChild(option);
        });
    }