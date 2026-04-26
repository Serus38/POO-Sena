import java.util.Scanner;
import java.util.ArrayList;

import model.Account;
import model.Bank;
import model.Client;
import model.Employed;
import model.Investment;
import model.Investor;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sn = new Scanner(System.in);
        Bank myBank = new Bank("My Bank", "123 Main St", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        boolean exit = false;
        int option;

        while (!exit) {
            System.out.println("\n==== Welcome to " + myBank.getName() + " ====");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Registrar inversionista y Oferta de inversion");
            System.out.println("4. Realizar Deposito");
            System.out.println("5. Realizar Retiro");
            System.out.println("6. Ver catalogo de inversiones");
            System.out.println("7. Consultar dias de vacaciones");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            option = sn.nextInt();
            sn.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Handle option 1: Register employee
                    System.out.println("Registrar empleado");
                    System.out.print("Ingrese el nombre del empleado: ");
                    String empName = sn.nextLine();
                    System.out.print("Ingrese el apellido del empleado: ");
                    String empLastName = sn.nextLine();
                    System.out.print("Ingrese el ID del empleado: ");
                    String empId = sn.nextLine();
                    System.out.print("Ingrese el userId del empleado: ");
                    String empUserId = sn.nextLine();
                    System.out.print("Ingrese el puesto del empleado: ");
                    String empPosition = sn.nextLine();
                    System.out.print("Ingrese los años de experiencia del empleado: ");
                    int empYearsExp = sn.nextInt();
                        sn.nextLine();

                    // Create and add the employee to the bank
                    Employed newEmployee = new Employed(empName, empLastName, empId, empUserId, "N/A", empPosition,
                            empYearsExp);
                    myBank.addEmployed(newEmployee);
                    break;

                case 2:
                    // Handle option 2: Register client
                    System.out.println("Registrar cliente");
                    System.out.print("Ingrese el nombre del cliente: ");
                    String clientName = sn.nextLine();
                    System.out.print("Ingrese el apellido del cliente: ");
                    String clientLastName = sn.nextLine();
                    System.out.print("Ingrese el ID del cliente: ");
                    String clientId = sn.nextLine();
                    System.out.print("Ingrese el userId del cliente: ");
                    String clientUserId = sn.nextLine();
                    System.out.print("Ingrese el numero de cuenta del cliente: ");
                    String clientAccountNumber = sn.nextLine();
                    System.out.print("Saldo inicial del cliente: ");
                    double clientInitialBalance = sn.nextDouble();
                    sn.nextLine();

                    // Create and add the client to the bank
                    Account clientAccount = new Account(clientAccountNumber, clientInitialBalance);
                    Client newClient = new Client(clientName, clientLastName, clientId, clientUserId, clientAccount);
                    myBank.addClient(newClient);
                    System.out.println("Cuenta creada con saldo inicial: " + clientInitialBalance);
                    break;

                case 3:
                    System.out.println("Registrar inversionista y oferta de inversion");
                    System.out.print("Ingrese el ID del inversionista: 5");
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
                    System.out.println("Realizar deposito");
                    System.out.print("Ingrese su userId: ");
                    String depUserId = sn.nextLine();
                    Client clientDep = myBank.findClientByUserId(depUserId);

                    if (clientDep == null) {
                        System.out.println("Cliente no encontrado");
                        break;
                    }

                    System.out.print("Ingrese su numero de cuenta: ");
                    String depAccountNumber = sn.nextLine();
                    System.out.print("Ingrese monto a depositar: ");
                    double depAmount = sn.nextDouble();
                    sn.nextLine();

                    clientDep.depositMoney(depAccountNumber, depUserId, depAmount);
                    break;

                case 5:
                    System.out.println("Realizar retiro");
                    System.out.print("Ingrese su userId: ");
                    String retUserId = sn.nextLine();
                    Client clientRet = myBank.findClientByUserId(retUserId);

                    if (clientRet == null) {
                        System.out.println("Cliente no encontrado");
                        break;
                    }

                    System.out.print("Ingrese su numero de cuenta: ");
                    String retAccountNumber = sn.nextLine();
                    System.out.print("Ingrese monto a retirar: ");
                    double retAmount = sn.nextDouble();
                    sn.nextLine();

                    clientRet.retireMoney(retAccountNumber, retUserId, retAmount);
                    break;

                case 6:
                    System.out.println("Catalogo de inversiones");
                    myBank.showAllInvestments();
                    break;

                case 7:
                    System.out.println("Consultar dias de vacaciones");
                    System.out.print("Ingrese userId del empleado: ");
                    String employedUserId = sn.nextLine();

                    Employed foundEmployed = null;
                    for (Employed employed : myBank.getEmployeds()) {
                        if (employed.getUserId().equals(employedUserId)) {
                            foundEmployed = employed;
                            break;
                        }
                    }

                    if (foundEmployed == null) {
                        System.out.println("Empleado no encontrado");
                    } else {
                        System.out.println("Dias de vacaciones de " + foundEmployed.getName() + ": "
                                + foundEmployed.daysOfVacation());
                    }
                    break;

                case 8:
                    exit = true;
                    System.out.println("Gracias por usar el sistema bancario");
                    break;

                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        }

        sn.close();
    }
}
