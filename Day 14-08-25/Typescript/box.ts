class Box<T> {
    value: T;

    constructor(value: T) {
        this.value = value;
    }

    getValue(): T {
        return this.value;
    }
}

let numBox = new Box<number>(123);
console.log(numBox.getValue()); 

let stringBox = new Box<string>("Hello");
console.log(stringBox.getValue()); 
