package model.DAOEntities;

import controller.ConnectionPool;
import model.Account;
import model.Transfer;
import model.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAOTransfer extends DAO {
    private static final String findAllSQL = "select id, sender_id, receiver_id, amount, transfer_date from transfer";
    private static final String findByClientSQL = "select sender_id, receiver_id, amount, transfer_date from transfer where sender_id = ?";
    private static final String findByIdSQL = "select sender_id, receiver_id, amount, transfer_date from transfer where id = ?";
    private static final String insertTransferSQL = "insert into transfer (id, sender_id, receiver_id, amount, transfer_date) values(?, ?, ?, ?, ?)";
    private static final String genNewIdSQL = "select max(id) from transfer";
    private static final String makePaimentSQL = "insert into transfer (id, sender_id, receiver_id, amount, transfer_date) values(?, ?, ?, ?, ?)";


    public List<Transfer> findAll() throws DAOException {
        List<Transfer> ret = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt =  connection.prepareStatement(findAllSQL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Transfer transfer = new Transfer();
                transfer.setId(rs.getInt(1));
                transfer.setSender_id(Integer.parseInt(rs.getString(2)));
                transfer.setReceiver_id(Integer.parseInt(rs.getString(3)));
                transfer.setAmount(Double.parseDouble(rs.getString(4)));
                transfer.setTransferDate(Date.valueOf(rs.getString(5)));
                ret.add(transfer);
            }

        } catch (SQLException e) {
            throw new DAOException("Find all students exception " + e.getMessage());
        }
        return ret;
    }


    public void insertTransfer(Transfer transfer) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(insertTransferSQL);
            stmt.setInt(1, transfer.getId());
            stmt.setInt(2, transfer.getSender_id());
            stmt.setInt(3, transfer.getReceiver_id());
            stmt.setDouble(4, transfer.getAmount());
            stmt.setDate(5, transfer.getTransferDate());
            stmt.execute();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Insert transfer exception " + e.getMessage());
        }
    }

    public void makePaiment(Account sender, Account receiver, double amount) throws DAOException, SQLException {
        Connection connection = ConnectionPool.acquireConnection();
        connection.setAutoCommit(false);
        // set auto-commit false
        try {
            PreparedStatement stmt = connection.prepareStatement(genNewIdSQL);
            ResultSet rs = stmt.executeQuery();
            Integer newId = 1000;
            while(rs.next()){
                newId = rs.getInt(1);
            }
            newId++;

            if(sender.getBalance() > amount) {
                sender.setBalance(sender.getBalance() - amount);
                receiver.setBalance(receiver.getBalance() + amount);
                DAOAccount daoAccount = new DAOAccount();
                daoAccount.updateAccount(sender);
                daoAccount.updateAccount(receiver);

                stmt = connection.prepareStatement(makePaimentSQL);
                stmt.setInt(1, newId);
                stmt.setInt(2, sender.getId());
                stmt.setInt(3, receiver.getId());
                stmt.setDouble(4, amount);
                stmt.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
                stmt.execute();
            }

        } catch (SQLException e) {
            throw new DAOException("Transfer exception " + e.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    public void getPeriodClientTransfersSum(List<Integer> clientIds, Date start, Date end) throws DAOException {
        List<Transfer> accounts = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.acquireConnection();
            double sum = 0;
            for(Integer id: clientIds) {
                PreparedStatement stmt = connection.prepareStatement(findByClientSQL);

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Date date = rs.getDate(4);
                    if(date.after(start) && date.before(end)) {
                        sum += rs.getDouble(3);
                    }
                }
            }
            System.out.println(sum);
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("count payments exception " + e.getMessage());
        }
    }

}
