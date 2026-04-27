import java.util.Scanner;
import java.util.ArrayList;

import model.Account;
import model.SavingAccount;
import model.InvestmentAccount;
import model.Bank;
import model.Client;
import model.Employed;
import model.Investment;
import model.Investor;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sn = new Scanner(System.in);
        // Inicialite the bank with empty lists for clients, employees, and investments
        Bank myBank = new Bank("My Bank", "Calle 34 #5-45", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        boolean exit = false;
        int option;

        while (!exit) {
            System.out.println("\n==== BIENVENIDO A " + myBank.getName().toUpperCase() + " ====");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Registrar Cliente");
            System.out.println("3. Registrar Casa Inversionista y Oferta");
            System.out.println("4. Realizar Depósito");
            System.out.println("5. Realizar Retiro (Requiere ID Física)");
            System.out.println("6. Ver Catálogo de Inversiones");
            System.out.println("7. Consultar Días de Vacaciones");
            System.out.println("8. Aplicar Intereses Mensuales (Solo Ahorro)");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            option = sn.nextInt();
            sn.nextLine(); // Clear buffer

            switch (option) {
                case 1:
                    System.out.println("\n--- Registro de Empleado ---");
                    System.out.print("Nombre: "); String empName = sn.nextLine();
                    System.out.print("Apellido: "); String empLastName = sn.nextLine();
                    System.out.print("ID: "); String empId = sn.nextLine();
                    System.out.print("ID Usuario: "); String empUser = sn.nextLine();
                    System.out.print("Número de cuenta nómina: "); String empAcc = sn.nextLine();
                    System.out.print("Puesto (Cajero/Supervisor/Recepcionista): "); String pos = sn.nextLine();
                    System.out.print("Años trabajando: "); int yrs = sn.nextInt();
                    
                    Employed newEmp = new Employed(empName, empLastName, empId, empUser, empAcc, pos, yrs);
                    myBank.addEmployed(newEmp);
                    System.out.println("Empleado registrado y cuenta de nómina activada.");
                    break;

                case 2:
                    System.out.println("\n--- Registro de Cliente ---");
                    System.out.print("Nombre: "); String cliName = sn.nextLine();
                    System.out.print("Apellido: "); String cliLastName = sn.nextLine();
                    System.out.print("ID: "); String cliId = sn.nextLine();
                    System.out.print("ID Usuario: "); String cliUser = sn.nextLine();
                    System.out.print("Número de cuenta: "); String cliAccNum = sn.nextLine();
                    
                    System.out.println("Tipo de cuenta: 1. Ahorro ($1,000 min) | 2. Inversión ($25,000 min)");
                    int type = sn.nextInt();
                    Account newAcc = null;

                    if (type == 1) {
                        System.out.print("Porcentaje ahorro anual: "); double p = sn.nextDouble();
                        newAcc = new SavingAccount(cliAccNum, p);
                        System.out.println("Ingrese el saldo inicial: " + "\n=== Recuerde que el saldo inicial minimo es 1000 ===\n");
                        double initialDeposit = sn.nextDouble();
                        if (initialDeposit < 1000) {
                            System.out.println("Error: Monto insuficiente para cuenta de ahorro.");
                            break;
                        }
                        if (!newAcc.deposit(initialDeposit)) {
                            System.out.println("Error: No se pudo crear la cuenta de ahorro.");
                            break;
                        }
                    } else {
                        System.out.print("Monto inicial de inversión: "); double initial = sn.nextDouble();
                        if (initial < 25000) {
                            System.out.println("Error: Monto insuficiente para cuenta de inversión.");
                            break;
                        }
                        newAcc = new InvestmentAccount(cliAccNum, initial);
                    }
                    
                    Client newCli = new Client(cliName, cliLastName, cliId, cliUser, newAcc);
                    myBank.addClient(newCli);
                    break;

                case 3:
                    System.out.println("\n--- Registro de Inversionista y Oferta ---");
                    System.out.print("Ingrese el ID del inversionista: ");
                    String investorId = sn.nextLine();
                    System.out.print("Ingrese el nombre del inversionista: ");
                    String investorName = sn.nextLine();
                    System.out.print("Ingrese el nivel de confianza (0-100): ");
                    double investmentTrust = sn.nextDouble();
                    System.out.print("Ingrese el retorno esperado (%): ");
                    double returnRate = sn.nextDouble();
                    sn.nextLine();

                    System.out.print("Ingrese nivel de riesgo de la inversion: ");
                    String riskLevel = sn.nextLine();
                    System.out.print("Ingrese monto minimo de la inversion: ");
                    double amountMinimum = sn.nextDouble();
                    System.out.print("Ingrese duracion en meses: ");
                    int durationMonths = sn.nextInt();
                    sn.nextLine();

                    Investor investor = new Investor(investorId, investorName, investmentTrust, returnRate);
                    Investment investment = new Investment(riskLevel, amountMinimum, durationMonths, investor);
                    myBank.addInvestmentSecure(investment);
                    break;

                case 4:
                    System.out.print("Ingrese su ID de usuario: ");
                    String dUser = sn.nextLine();
                    Client cDep = myBank.findClientByUserId(dUser);
                    if (cDep != null) {
                        System.out.print("Confirme su número de cuenta: "); String dAcc = sn.nextLine();
                        System.out.print("Monto a depositar: "); double dAmount = sn.nextDouble();
                        cDep.depositMoney(dAcc, dUser, dAmount);
                    } else { System.out.println("Usuario no encontrado."); }
                    break;

                case 5:
                    System.out.print("Ingrese su ID de usuario: ");
                    String rUser = sn.nextLine();
                    Client cRet = myBank.findClientByUserId(rUser);
                    if (cRet != null) {
                        System.out.print("Muestre su ID Física: "); String physicalId = sn.nextLine();
                        System.out.print("Confirme su número de cuenta: "); String rAcc = sn.nextLine();
                        System.out.print("Monto a retirar: "); double rAmount = sn.nextDouble();
                        // Llamamos al método que ajustamos en la clase Client
                        cRet.retireMoney(rAcc, rUser, physicalId, rAmount);
                    } else { System.out.println("Usuario no encontrado."); }
                    break;

                case 6:
                    System.out.println("Catalogo de inversiones");
                    myBank.showAllInvestments();
                    break;

                case 7:
                    System.out.print("Ingrese ID de usuario del empleado: ");
                    String eUser = sn.nextLine();
                    Employed fEmp = null;
                    for (Employed e : myBank.getEmployeds()) {
                        if (e.getUserId().equals(eUser)) { fEmp = e; break; }
                    }
                    if (fEmp != null) {
                        System.out.println("Días de vacaciones para " + fEmp.getName() + ": " + fEmp.daysOfVacation());
                    } else { System.out.println("Empleado no encontrado."); }
                    break;

                case 8:
                    System.out.println("Aplicando intereses mensuales a cuentas de ahorro...");
                    for (Client c : myBank.getClients()) {
                        if (c.getAccount() instanceof SavingAccount) {
                            ((SavingAccount) c.getAccount()).applyMonthlyInterest();
                        }
                    }
                    System.out.println("Intereses aplicados con éxito.");
                    break;

                case 9:
                    exit = true;
                    System.out.println("Cerrando sesión...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        sn.close();
    }
}