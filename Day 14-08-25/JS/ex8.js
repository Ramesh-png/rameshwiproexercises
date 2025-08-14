  const arr1 = [1, 2, 3];
    const arr2 = [4, 5, 6];

    const newArr = [4, 5, ...arr1, 6, 7, ...arr2, 8, 9];

    document.getElementById("result").textContent = JSON.stringify(newArr);