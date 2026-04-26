package model;

public class Client extends Person {
    // Attributes
    private String userId;
    private Account account;

    // Constructor
    public Client(String name, String lastName, String id, String userId, Account account) {
        super(name, lastName, id);
        this.userId = userId;
        this.account = account;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return account.getAccountNumber();
    }

    public void setAccountNumber(String accountNumber) {
        this.account.setAccountNumber(accountNumber);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    // Verify if is a client of the bank and if the account number is correct
    public void retireMoney(String accountNumber, String userId, double amount) {
        // 1. Verify if the userId and accountNumber match with the client's information
        if (!this.userId.equals(userId)) {
            System.out.println("Usuario incorrecto");
            return;
        }

        // 2. Verify if the account number is correct
        if (!this.account.getAccountNumber().equals(accountNumber)) {
            System.out.println("Numero de cuenta incorrecto");
            return;
        }

        // 3. If both are correct, proceed to retire money from the account
        if (amount <= 0) {
            System.out.println("El monto debe ser mayor a 0");
            return;
        }

        boolean transSuccess = this.account.retire(amount);
        if (transSuccess) {
            System.out.println("Retiro exitoso. Saldo actual: " + this.account.getBalance());
        } else {
            System.out.println("Saldo insuficiente");
        }

    }

    public void depositMoney(String accountNumber, String userId, double amount) {
        if (!this.userId.equals(userId)) {
            System.out.println("Usuario incorrecto");
            return;
        }

        if (!this.account.getAccountNumber().equals(accountNumber)) {
            System.out.println("Numero de cuenta incorrecto");
            return;
        }

        if (amount <= 0) {
            System.out.println("El monto debe ser mayor a 0");
            return;
        }

        this.account.deposit(amount);
        System.out.println("Deposito exitoso. Saldo actual: " + this.account.getBalance());
    }

}
