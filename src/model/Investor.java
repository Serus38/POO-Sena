package model;

public class Investor {
    // Attributes
    private String investorId;
    private String name;
    private double investmentTrust;
    private double returnRate;

    // Constant for minimum trust level required to invest
    public static final double MINIMUM_TRUST_REQUIRED = 65.0;

    // Constructor
    public Investor(String investorId, String name, double investmentTrust, double returnRate) {
        this.investorId = investorId;
        this.name = name;
        this.investmentTrust = investmentTrust;
        this.returnRate = returnRate;
    }

    // Method to check if the investor is eligible to invest based on their trust level
    public static boolean checkEligibility(double trustLevel) {
        return trustLevel >= MINIMUM_TRUST_REQUIRED;
    }

    // Getters and Setters
    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInvestmentTrust() {
        return investmentTrust;
    }

    public void setInvestmentTrust(double investmentTrust) {
        this.investmentTrust = investmentTrust;
    }

    public double getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(double returnRate) {
        this.returnRate = returnRate;
    }

}