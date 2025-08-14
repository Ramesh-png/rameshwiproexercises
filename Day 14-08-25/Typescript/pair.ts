function pair<T, U>(first: T, second: U): [T, U] {
    return [first, second];
}

let numStrPair = pair<number, string>(1, "Ram");
let boolDatePair = pair<boolean, Date>(true, new Date());

console.log(numStrPair);
console.log(boolDatePair);
