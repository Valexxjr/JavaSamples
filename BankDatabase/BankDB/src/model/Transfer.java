package model;

import java.sql.Date;

public class Transfer {
    private int id;
    private int sender_id;
    private int receiver_id;
    private double amount;
    private Date transferDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", sender_id=" + sender_id +
                ", receiver_id=" + receiver_id +
                ", amount=" + amount +
                ", transferDate=" + transferDate +
                '}';
    }
}
