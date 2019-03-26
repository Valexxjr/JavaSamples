package model.DAOEntities;

import model.Account;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAOAccount {

    private EntityManager entityManager;

    public DAOAccount(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Account> findAll() {
        TypedQuery<Account> namedQuery = entityManager.createNamedQuery("Account.findAll", Account.class);
        return namedQuery.getResultList();
    }

    public int insert(Account account) {
        entityManager.getTransaction().begin();
        Account accountDB = entityManager.merge(account);
        entityManager.getTransaction().commit();
        return accountDB.getId();
    }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public Account findById(int id) {
        return entityManager.find(Account.class, id);
    }

    public void lockAccount(Account acc) {
        Account account = entityManager.find(Account.class, acc.getId());

        entityManager.getTransaction().begin();
        account.setLocked(true);
        entityManager.getTransaction().commit();
    }

    public void unlockAccount(Account acc) {
        Account account = entityManager.find(Account.class, acc.getId());

        entityManager.getTransaction().begin();
        account.setLocked(false);
        entityManager.getTransaction().commit();
    }

}
