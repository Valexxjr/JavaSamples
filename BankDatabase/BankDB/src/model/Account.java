package model;

public class Account {
    private int id;
    private double balance;
    private int holder;
    private boolean locked;

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

    public int getHolder() {
        return holder;
    }

    public void setHolder(int holder) {
        this.holder = holder;
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
                ", holder=" + holder +
                ", locked=" + locked +
                '}';
    }
}
