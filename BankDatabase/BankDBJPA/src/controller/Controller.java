package controller;

import model.*;
import model.DAOEntities.DAOAccount;
import model.DAOEntities.DAOClient;
import model.DAOEntities.DAOTransfer;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class Controller {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public Controller() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Bank");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Client> getAllClients() {
        return new DAOClient(entityManager).findAll();
    }

    public Collection<Account> getClientAccounts(int id) {
        return new DAOClient(entityManager).get(id).getAccounts();
    }

    public Collection<Account> getClientAccountsCriteria(Client client) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> account = cq.from(Account.class);
        cq.where(cb.equal(account.get(Account_.holder), client));
        cq.select(account);
        TypedQuery<Account> q = entityManager.createQuery(cq);
        List<Account> accounts = q.getResultList();
        return accounts;
    }


    public List<Transfer> getAllTransfers() {
        Logger.info("Getting all transfers");
        return new DAOTransfer(entityManager).findAll();
    }

    public List<Account> getAllAccounts() {
        Logger.info("Getting all accounts");
        return new DAOAccount(entityManager).findAll();
    }

    public List<Account> getAllAccountsCriteria() {
        Logger.info("Getting all accounts Criteria");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> account = cq.from(Account.class);
        cq.select(account);
        TypedQuery<Account> q = entityManager.createQuery(cq);
        List<Account> allStudents = q.getResultList();
        return allStudents;
    }

    public double getPeriodTransfersSum(Date startDate, Date endDate, Client client) {
        Logger.info("Sum of periodical transfers");
        double res = 0;
        for (Account account : getAccountsByClient(client)) {
            res += entityManager.createNamedQuery("periodTransfersSum", Double.class)
                    .setParameter("startdate", startDate)
                    .setParameter("enddate", endDate)
                    .setParameter("sender", account).getFirstResult();
        }
        return res;
    }

    public List<Transfer> getClientTransfersCriteria(Client client) {
        Logger.info("Getting all transfers Criteria");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transfer> cq = cb.createQuery(Transfer.class);
        Root<Transfer> transfer = cq.from(Transfer.class);
        List<Transfer> allTransfers = new ArrayList<>();
        for (Account account: getClientAccounts(client.getId())) {
            cq.select(transfer).where(cb.equal(transfer.get(Transfer_.sender), account));
            TypedQuery<Transfer> q = entityManager.createQuery(cq);
            List<Transfer> current = q.getResultList();
            allTransfers.addAll(current);
        }

        return allTransfers;
    }

    public double getPeriodTransfersSumCriteria(Date startDate, Date endDate, Client client) {
        Logger.info("Sum of transfers");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        double res = 0;
        Root<Transfer> transfer = cq.from(Transfer.class);

        for(Transfer clientTransfer: getClientTransfersCriteria(client)) {
            cq.select(cb.sum(transfer.get(Transfer_.amount)))
                    .where(cb.between(transfer.get(Transfer_.transfer_date), startDate, endDate));
            TypedQuery<Double> q = entityManager.createQuery(cq);
            res += q.getSingleResult();
        }

        return res;
    }

    public double getClientAccountsSum(Client client) {
        Logger.info("Sum of accounts");
        double res = 0;
        for (Account account : getAccountsByClient(client)) {
            res += account.getBalance();
        }
        return res;
    }

    public double getClientAccountsSumCriteria(Client client) {
        Logger.info("Sum of accounts");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);

        Root<Account> account = cq.from(Account.class);
        cq.select(cb.sum(account.get(Account_.balance))).where(cb.equal(account.get(Account_.holder), client));
        TypedQuery<Double> q = entityManager.createQuery(cq);
        double res = q.getSingleResult();
        return res;
    }

    public List<Account> getAccountsByClient(Client client) {
        return entityManager.createNamedQuery("findAllAccountsByClient", Account.class).setParameter("client", client).getResultList();
    }

    public void makePayment(Account sender, Account receiver, double amount) {

        if (sender.getBalance() > amount) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            DAOAccount daoAccount = new DAOAccount(entityManager);
            daoAccount.insert(sender);
            daoAccount.insert(receiver);
            Transfer transfer = new Transfer(sender, receiver, amount, new Date(Calendar.getInstance().getTimeInMillis()));
            DAOTransfer daoTransfer = new DAOTransfer(entityManager);
            daoTransfer.insert(transfer);
        }
    }


    public void makePaymentCriteria(Account sender, Account receiver, double amount) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Account> updateAccount = cb.createCriteriaUpdate(Account.class);
        Root<Account> accountRoot = updateAccount.from(Account.class);

        if (sender.getBalance() > amount) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            updateAccount.set(Account_.balance, sender.getBalance());
            updateAccount.where(cb.equal(accountRoot.get(Account_.id), sender.getId()));
            entityManager.createQuery(updateAccount).executeUpdate();

            updateAccount.set(Account_.balance, receiver.getBalance());
            updateAccount.where(cb.equal(accountRoot.get(Account_.id), receiver.getId()));
            entityManager.createQuery(updateAccount).executeUpdate();



            Transfer transfer = new Transfer(sender, receiver, amount, new Date(Calendar.getInstance().getTimeInMillis()));
            DAOTransfer daoTransfer = new DAOTransfer(entityManager);
            daoTransfer.insert(transfer);
            entityTransaction.commit();
        }
    }



    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
