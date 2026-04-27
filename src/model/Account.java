package model;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected boolean pendingFirstDeposit;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        // If the initial balance is 0, we consider that the first deposit is pending
        this.pendingFirstDeposit = (initialBalance == 0);
    }

    public abstract boolean deposit(double amount);
    public abstract boolean retire(double amount);

    // Getters and Setters
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
}