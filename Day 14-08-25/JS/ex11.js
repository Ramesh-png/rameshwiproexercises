const separateFirstRest = (first, ...rest) => {
    return {
        first: first,
        rest: rest
    };
};

console.log(separateFirstRest(10, 20, 30, 40));

console.log(separateFirstRest("apple", "banana", "cherry", "dates"));