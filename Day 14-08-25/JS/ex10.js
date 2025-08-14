    const sumNumbers = (...nums) => {
        let sum = 0;
        for (let num of nums) {
            sum += num;
        }
        return sum;
    };

    const total = sumNumbers(10, 20, 30, 40, 50);

    document.getElementById("result").textContent = `Sum: ${total}`;