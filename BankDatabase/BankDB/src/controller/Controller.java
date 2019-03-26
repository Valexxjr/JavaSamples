package controller;

import model.Account;
import model.Client;
import model.DAOEntities.DAOAccount;
import model.DAOEntities.DAOClient;
import model.DAOEntities.DAOTransfer;
import model.exception.DAOException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public void printAllAccounts(Client client) throws DAOException {
        for (Account account : new DAOAccount().getClientAccounts(client)) {
            System.out.println(account);
        }
    }

    public void periodPaimentsSum(Client client, Date start, Date finish) throws DAOException {
        List<Integer> ids = new ArrayList<>();

        for (Account account : new DAOAccount().getClientAccounts(client)) {
            ids.add(account.getId());
        }

        new DAOTransfer().getPeriodClientTransfersSum(ids, start, finish);

    }

    public void allAccountsSum(Client client) throws DAOException {
        double sum = 0;
        for (Account account : new DAOAccount().getClientAccounts(client)) {
            sum += account.getBalance();
        }

        System.out.println("Client " + client.getName() + " total balance : " + sum );
    }

    public void printAllAccounts() throws DAOException {
        for (Account account : new DAOAccount().findAll()) {
            System.out.println(account);
        }
    }

    public void printAllClients() throws DAOException {
        for (Client client : new DAOClient().findAll()) {
            System.out.println(client);
        }
    }
}
