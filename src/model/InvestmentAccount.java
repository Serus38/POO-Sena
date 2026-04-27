package model;

public class InvestmentAccount extends Account {
    private static final double MIN_INITIAL_AMOUNT = 25000.0;
    private static final double MIN_STAY_BALANCE = 10000.0;

    public InvestmentAccount(String accountNumber, double initialAmount) {
        super(accountNumber, initialAmount);
        if (initialAmount < MIN_INITIAL_AMOUNT) {
            System.out.println("Alerta: El monto inicial debe ser al menos " + MIN_INITIAL_AMOUNT);
            this.balance = 0;
            this.pendingFirstDeposit = true;
        } else {
            this.pendingFirstDeposit = false;
        }
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean retire(double amount) {
        // Total withdrawal: Allow if the amount is exactly the current balance (closing the account)
        if (amount == this.balance) {
            this.balance = 0;
            System.out.println("Cuenta de inversión cancelada. Saldo retirado por completo.");
            return true;
        }

        // Partial withdrawal: Allow only if the remaining balance will be >= MIN_STAY_BALANCE
        if (this.balance - amount >= MIN_STAY_BALANCE) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Error: Para retiros parciales, el saldo debe ser >= " + MIN_STAY_BALANCE);
            return false;
        }
    }
}