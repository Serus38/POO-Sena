package model;

public class Investment {
    // Attributes
    private String riskLevel;
    private double amountMinimum;
    private int durationMonths;
    private Investor investor;

    // Constructor
    public Investment(String riskLevel, double amountMinimum, int durationMonths, Investor investor) {
        this.riskLevel = riskLevel;
        this.amountMinimum = amountMinimum;
        this.durationMonths = durationMonths;
        this.investor = investor;
    }

    // Getters and Setters
    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public double getAmountMinimum() {
        return amountMinimum;
    }

    public void setAmountMinimum(double amountMinimum) {
        this.amountMinimum = amountMinimum;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public void showInvestmentDetails() {
        System.out.println("Risk Level: " + riskLevel);
        System.out.println("Minimum Amount: " + amountMinimum);
        System.out.println("Duration (months): " + durationMonths);
        System.out.println("Investor Name: " + investor.getName());
    }

    
    
}
