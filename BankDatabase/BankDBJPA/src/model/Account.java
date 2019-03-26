package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Client's account model
 */

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(
                name = "cleanAccounts",
                query = "delete from Account"),
        @NamedQuery(
                name = "findAllAccountsByClient",
                query = "select a from Account a where a.holder = :client"),
        @NamedQuery(name = "Account.findAll",
                query = "select a from Account a")
}
)

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "locked")
    private boolean locked;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "holder")
    private Client holder;

    public Account() {
    }

    public Account(double balance, Client holder, boolean locked) {
        this.balance = balance;
        this.locked = locked;
        this.holder = holder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", locked=" + locked +
                ", holder=" + holder.toString() +
                '}';
    }
}
