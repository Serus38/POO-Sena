package model;

public class Employed extends Client {
    private String position;
    private int yearsOfExperience;

    // Constructor
    public Employed(String name, String lastName, String id, String userId, String accountNumber, String position,
                    int yearsOfExperience) {
        // ERROR FIX: Como Account es abstracta, debemos pasarle una implementación.
        // Lo más lógico es que un empleado tenga una Cuenta de Ahorros para su nómina.
        // Usamos 0.0 de interés inicial o el que definas por defecto.
        super(name, lastName, id, userId, new SavingAccount(accountNumber, 0.0));
        
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters y Setters se mantienen igual...

    /**
     * Tu lógica es excelente aquí. 
     * Año 1 = 5 días.
     * Año 2 = 7 días... hasta tope de 20.
     */
    public int daysOfVacation() {
        if (yearsOfExperience < 1)
            return 0;
        int days = 5 + ((yearsOfExperience - 1) * 2);
        return Math.min(days, 20);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    
}