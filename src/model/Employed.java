package model;

public class Employed extends Client {
    // Attributes
    private String position;
    private int yearsOfExperience;

    // Constructor
    public Employed(String name, String lastName, String id, String userId, String accountNumber, String position,
            int yearsOfExperience) {
        super(name, lastName, id, userId, new Account(accountNumber, 0));
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters and Setters
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

    // Method to calculate days of vacation based on years of experience
    public int daysOfVacation() {
        if (yearsOfExperience < 1)
            return 0;
        int days = 5 + ((yearsOfExperience - 1) * 2);
        return Math.min(days, 20);

    }
}
