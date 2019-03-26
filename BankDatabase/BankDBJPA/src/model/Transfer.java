package model;

import javax.persistence.*;
import java.sql.Date;
/**
 * Transfer model
 */

@Entity
@Table(name = "transfer")
@NamedQueries({
        @NamedQuery(
                name = "cleanTransfers",
                query = "delete from Transfer"),
        @NamedQuery(
                name = "periodTransfersSum",
                query = "select sum(amount) from Transfer t " +
                        "where t.sender = :sender and transfer_date >= :startdate and transfer_date < :enddate"),
        @NamedQuery(
                name = "Transfer.findAll",
                query = "select t from Transfer t")
})
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALLf)
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    @Column(name = "amount")
    private double amount;

    @Column(name = "transfer_date")
    private Date transfer_date;

    public Transfer() { }

    public Transfer( Account sender, Account receiver, double amount, Date transfer_date) {

        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.transfer_date = transfer_date;
    }

    public int getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransfer_date() {
        return transfer_date;
    }

    public void setTransfer_date(Date transferDate) {
        this.transfer_date = transferDate;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", transfer_date=" + transfer_date +
                '}';
    }
}
