var Calculator = /** @class */ (function () {
    function Calculator() {
    }
    Calculator.prototype.add = function (a, b) {
        return a + b;
    };
    Calculator.prototype.subtract = function (a, b) {
        return a - b;
    };
    return Calculator;
}());
// Example usage
var calc = new Calculator();
console.log("Addition: ", calc.add(10, 5)); // 15
console.log("Subtraction: ", calc.subtract(10, 5)); // 5
