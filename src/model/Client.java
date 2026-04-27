package model;

public class Client extends Person {
    private String userId;
    private Account account;

    public Client(String name, String lastName, String id, String userId, Account account) {
        super(name, lastName, id);
        this.userId = userId;
        this.account = account;
    }

    // --- MÉTODOS DE OPERACIÓN ---

    /**
     * Ajustado: Ahora pide la identificación física como dice el enunciado.
     */
    public void retireMoney(String accountNumber, String userId, String physicalId, double amount) {
        // 1. Validar usuario y cuenta
        if (!this.userId.equals(userId) || !this.account.getAccountNumber().equals(accountNumber)) {
            System.out.println("Error: Credenciales de usuario o cuenta inválidas.");
            return;
        }

        // 2. REGLA DEL ENUNCIADO: Validar identificación física
        // Comparamos la identificación que traen por parámetro con la heredada de Person (super.getId())
        if (!this.getId().equals(physicalId)) {
            System.out.println("Error: La identificación física no coincide con el titular de la cuenta.");
            return;
        }

        // 3. Intentar realizar el retiro
        // Eliminamos el "amount <= 1000" porque el cliente puede retirar lo que quiera 
        // siempre que la cuenta (Savings o Investment) permita dejar el mínimo.
        boolean transSuccess = this.account.retire(amount);
        
        if (transSuccess) {
            System.out.println("Retiro exitoso de $" + amount + ". Saldo actual: $" + this.account.getBalance());
        }
        // No hace falta un 'else' aquí porque Account.retire() ya imprime sus propios errores de saldo mínimo.
    }

    public void retireMoney(String accountNumber, String userId, double amount) {
        this.retireMoney(accountNumber, userId, this.getId(), amount);
    }

    public void depositMoney(String accountNumber, String userId, double amount) {
        if (!this.userId.equals(userId) || !this.account.getAccountNumber().equals(accountNumber)) {
            System.out.println("Error: Usuario o cuenta incorrectos.");
            return;
        }

        // Delegamos la validación del monto (como los $1,000 iniciales) a la cuenta
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