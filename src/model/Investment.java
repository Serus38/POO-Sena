package model;

public class Investment {
    // Attributes
    private String riskLevel;
    private double amountMinimum;
    private int durationMonths;
    private Investor investor;

    // Constants for risk levels
    public static final String LOW_RISK = "Low";
    public static final String MEDIUM_RISK = "Medium";
    public static final String HIGH_RISK = "High";

    public static final double BANK_FEE_PERCENTAGE = 1.5; // 1.5% fee on the invested amount

    // Method to calculate the projected return based on the amount, return rate,
    // and duration
    public static double calculateProjection(double amount, double returnRate, int months) {
        double monthlyReturnRate = returnRate / 12 / 100;
        return amount * Math.pow(1 + monthlyReturnRate, months);
    }

    // Method to get a description of the risk level
    public static String getRiskDescription(int riskLevel) {
        switch (riskLevel) {
            case 1:
                return LOW_RISK;
            case 2:
                return MEDIUM_RISK;
            case 3:
                return HIGH_RISK;
            default:
                return "Unknown";
        }
    }

    public static boolean isValidRiskLevel(String risk, double returnRate) {
        // Si el riesgo es DESCONOCIDO (opción inválida en el menú)
        if (risk.equals("Unknown"))
            return false;

        // REGLA: El retorno debe ser proporcional al riesgo
        return switch (risk) {
            case LOW_RISK -> returnRate <= 10.0; // Riesgo bajo: máximo 10% retorno
            case MEDIUM_RISK -> returnRate <= 20.0; // Riesgo medio: máximo 20% retorno
            case HIGH_RISK -> returnRate > 20.0; // Riesgo alto: obligatorio más de 20% retorno
            default -> false;
        };
    }

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
        System.out.println("\n--- Oferta de Inversión ---");
        System.out.println("Casa (Clave): " + investor.getInvestorId());
        System.out.println("Nombre: " + investor.getName());
        System.out.println("Nivel de Riesgo: " + riskLevel);
        System.out.println("Retorno Esperado: " + investor.getReturnRate() + "%");
        System.out.println("Monto Mínimo: $" + amountMinimum);
        System.out.println("Plazo: " + durationMonths + " meses");
        System.out.println("Confiabilidad de la Casa: " + investor.getInvestmentTrust() + "%");
    }

    public double calculateBankFee(double investmentAmount) {
        return (investmentAmount * BANK_FEE_PERCENTAGE) / 100;
    }

}
