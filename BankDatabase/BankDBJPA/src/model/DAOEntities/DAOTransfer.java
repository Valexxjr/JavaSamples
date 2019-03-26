package model.DAOEntities;

import model.Transfer;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAOTransfer {
    private EntityManager entityManager;

    public DAOTransfer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Transfer> findAll() {
        TypedQuery<Transfer> namedQuery = entityManager.createNamedQuery("Transfer.findAll", Transfer.class);
        return namedQuery.getResultList();
    }

    public int insert(Transfer transfer) {
        entityManager.getTransaction().begin();
        Transfer transferDB = entityManager.merge(transfer);
        entityManager.getTransaction().commit();
        return transferDB.getId();
    }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public Transfer findById(int id) {
        return entityManager.find(Transfer.class, id);
    }

}
