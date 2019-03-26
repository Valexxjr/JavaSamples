package model.DAOEntities;

import model.Client;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

public class DAOClient {

    private EntityManager entityManager;

    public DAOClient(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Client> findAll() {
        TypedQuery<Client> namedQuery = entityManager.createNamedQuery("Client.findAll", Client.class);
        return namedQuery.getResultList();
    }

    public Client get(int id) {
        return entityManager.find(Client.class, id);
    }

    public int insert(Client client) {
        entityManager.getTransaction().begin();
        Client clientDB = entityManager.merge(client);
        entityManager.getTransaction().commit();
        return clientDB.getId();
    }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public Client findById(int id) {
        return entityManager.find(Client.class, id);
    }
}
