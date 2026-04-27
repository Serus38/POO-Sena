package model;

public class Client extends Person {
    private String userId;
    private Account account;

    public Client(String name, String lastName, String id, String userId, Account account) {
        super(name, lastName, id);
        this.userId = userId;
        this.account = account;
    }

    // Validate user credentials and perform a withdrawal
    public void retireMoney(String accountNumber, String userId, String physicalId, double amount) {
        if (!this.userId.equals(userId) || !this.account.getAccountNumber().equals(accountNumber)) {
            System.out.println("Error: Credenciales de usuario o cuenta inválidas.");
            return;
        }
        // Validate that the physical ID matches the client's ID
        if (!this.getId().equals(physicalId)) {
            System.out.println("Error: La identificación física no coincide con el titular de la cuenta.");
            return;
        }

        boolean transSuccess = this.account.retire(amount);

        if (transSuccess) {
            System.out.println("Retiro exitoso de $" + amount + ". Saldo actual: $" + this.account.getBalance());
        }
    }

    public void retireMoney(String accountNumber, String userId, double amount) {
        this.retireMoney(accountNumber, userId, this.getId(), amount);
    }

    public void depositMoney(String accountNumber, String userId, double amount) {
        if (!this.userId.equals(userId) || !this.account.getAccountNumber().equals(accountNumber)) {
            System.out.println("Error: Usuario o cuenta incorrectos.");
            return;
        }
        boolean success = this.account.deposit(amount);

        if (success) {
            System.out.println("Depósito exitoso. Saldo actual: $" + this.account.getBalance());
        }
    }

    // --- GETTERS Y SETTERS ---
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}