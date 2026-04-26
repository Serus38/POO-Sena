package model;

import java.util.List;

public class Bank {
    // Attributes
    private String name;
    private String address;
    private List<Client> clients;
    private List<Employed> employeds;
    private List<Investment> investments;

    // Constructor
    public Bank(String name, String address, List<Client> clients, List<Employed> employeds, List<Investment> investments) {
        this.name = name;
        this.address = address;
        this.clients = clients;
        this.employeds = employeds;
        this.investments = investments;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Employed> getEmployeds() {
        return employeds;
    }

    public void setEmployeds(List<Employed> employeds) {
        this.employeds = employeds;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    // -- Personal gestions --
    public void addClient(Client client) {
        clients.add(client);
        System.out.println("Cliente agregado: " + client.getName() + " " + client.getLastName());
    }

    // -- Employee gestions --
    public void addEmployed(Employed employed) {
        employeds.add(employed);
        System.out.println("Empleado agregado: " + employed.getName() + " " + employed.getLastName());
    }

    // -- Investment gestions --
    public void addInvestmentSecure(Investment investment) {
        if (investment.getInvestor().getInvestmentTrust() > 70) {
            investments.add(investment);
            System.out.println("Inversion segura agregada para el inversor: " + investment.getInvestor().getName());
        } else {
            System.out.println("Inversion no segura, no se agrego para el inversor: " + investment.getInvestor().getName());
        }
    }

    public void showAllInvestments() {
        System.out.println("Inversiones del banco:");
        for (Investment investment : investments) {
            investment.showInvestmentDetails();
            System.out.println("-----------------------");
        }
    }

    //-- Bank operations --
    public Client findClientByUserId(String userId) {
        for (Client client : clients) {
            if (client.getUserId().equals(userId)) {
                return client;
            }
        }
        return null; // Return null if no client is found with the given userId
    }
    


    
}
