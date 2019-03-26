package model;


import javax.persistence.*;
import java.util.Collection;

/**
 * Client's model
 */

@Entity
@Table(name = "client")
@NamedQueries({
        @NamedQuery(
                name = "Client.findAll",
                query = "select c from Client c"),
        @NamedQuery(
                name = "allAccountsSum",
                query = "select sum(balance) from Account a where a.holder = :client")
}
)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "holder", fetch = FetchType.EAGER)
    private Collection<Account> accounts;

    public Client() { }

    public Client(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
