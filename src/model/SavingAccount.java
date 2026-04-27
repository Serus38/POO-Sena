package model;

public class SavingAccount extends Account {
    private double annualInterestPercentage;
    private static final double MIN_BALANCE = 500.0;
    private static final double REQUIRED_FIRST_DEPOSIT = 1000.0;

    public SavingAccount(String accountNumber, double interestRate) {
        super(accountNumber, 0.0); // Inicia en 0 para obligar al primer depósito
        this.annualInterestPercentage = interestRate;
    }

    @Override
    public boolean deposit(double amount) {
        if (pendingFirstDeposit) {
            if (amount >= REQUIRED_FIRST_DEPOSIT) {
                this.balance = amount;
                this.pendingFirstDeposit = false;
                return true;
            }
            System.out.println("Error: El primer depósito debe ser exactamente " + REQUIRED_FIRST_DEPOSIT);
            return false;
        }
        
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean retire(double amount) {
        if (this.balance - amount >= MIN_BALANCE) {
            this.balance -= amount;
            return true;
        }
        System.out.println("Error: El saldo no puede ser menor a " + MIN_BALANCE);
        return false;
    }

    // Method to apply monthly interest to the balance
    public void applyMonthlyInterest() {
        double monthlyRate = (annualInterestPercentage / 12) / 100;
        this.balance += (this.balance * monthlyRate);
    }
}
