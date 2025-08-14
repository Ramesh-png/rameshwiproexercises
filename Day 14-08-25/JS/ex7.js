    const obj1 = { a: 1, b: 2 };
    const obj2 = { b: 3, c: 4 };

    const mergedObj = { ...obj1, ...obj2 };

    document.getElementById("result").textContent = JSON.stringify(mergedObj);

    console.log(mergedObj);  