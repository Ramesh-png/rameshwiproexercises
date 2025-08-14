class BankAccount {
    constructor(accountNumber, balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    deposit(amount) {
        if (amount > 0) {
            this.balance += amount;
            console.log(`Deposited ₹${amount}. New Balance: ₹${this.balance}`);
        } else {
            console.log("Deposit amount must be positive!");
        }
    }

    withdraw(amount) {
        if (amount <= 0) {
            console.log("Withdrawal amount must be positive!");
        } else if (amount > this.balance) {
            console.log("Insufficient funds!");
        } else {
            this.balance -= amount;
            console.log(`Withdrew ₹${amount}. New Balance: ₹${this.balance}`);
        }
    }
}

let myAccount = new BankAccount("123456", 5000);

myAccount.deposit(1500);  
myAccount.withdraw(2000);
myAccount.withdraw(6000); 