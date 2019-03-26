package model.DAOEntities;

import controller.ConnectionPool;
import model.Account;
import model.Client;
import model.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAccount extends DAO {
    private static final String findAllSQL = "select id, balance, holder, locked from account";
    private static final String findById = "select id, balance, holder, locked from account where id = ?";
    private static final String findByClientSQL = "select id, balance, holder, locked from account where holder = ?";
    private static final String insertAccountSQL = "insert into account (id, balance, holder, locked) values(?, ?, ?, ?)";
    private static final String blockAccountSQL = "update account set locked = 1 where id = ?";
    private static final String unlockAccountSQL = "update account set locked = 0 where id = ?";
    private static final String updateAccountSQL = "update account set balance = ?, holder = ?, locked = ? where id = ?";
    private static final String deleteAccountSQL = "delete account where id = ?";

    public List<Account> findAll() throws DAOException {
        List<Account> ret = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt =  connection.prepareStatement(findAllSQL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setBalance(Double.parseDouble(rs.getString(2)));
                account.setHolder(Integer.parseInt(rs.getString(3)));
                account.setLocked(Boolean.parseBoolean(rs.getString(4)));
                ret.add(account);
            }
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Find all students exception " + e.getMessage());
        }
        return ret;
    }

    public List<Account> getClientAccounts(Client client) throws DAOException {
        List<Account> accounts = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(findByClientSQL);
            stmt.setInt(1, client.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setBalance(rs.getDouble(2));
                account.setHolder(rs.getInt(3));
                account.setLocked(rs.getBoolean(4));
                accounts.add(account);
            }
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Insert student exception " + e.getMessage());
        }
        return accounts;
    }

    public void blockAccount(Account account) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(blockAccountSQL);
            stmt.setInt(1, account.getId());
            stmt.execute();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Block account exception " + e.getMessage());
        }
    }

    public Account getAccount(int id) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(findById);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Account account = new Account();
            while(rs.next()){

                account.setId(rs.getInt(1));
                account.setBalance(Double.parseDouble(rs.getString(2)));
                account.setHolder(Integer.parseInt(rs.getString(3)));
                account.setLocked(Boolean.parseBoolean(rs.getString(4)));
            }
            ConnectionPool.releaseConnection(connection);
            return account;
        } catch (SQLException e) {
            throw new DAOException("Block account exception " + e.getMessage());
        }
    }

    public void unlockAccount(Account account) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(unlockAccountSQL);
            stmt.setInt(1, account.getId());
            stmt.execute();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Unlock account exception " + e.getMessage());
        }
    }

    public void updateAccount(Account account) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(updateAccountSQL);
            stmt.setDouble(1, account.getBalance());
            stmt.setInt(2, account.getHolder());
            stmt.setBoolean(3, account.isLocked());
            stmt.setInt(4, account.getId());
            stmt.execute();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Update account exception " + e.getMessage());
        }
    }

    public void updateAccount(Account account) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(updateAccountSQL);
            stmt.setDouble(1, account.getBalance());
            stmt.setInt(2, account.getHolder());
            stmt.setBoolean(3, account.isLocked());
            stmt.setInt(4, account.getId());
            stmt.execute();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Update account exception " + e.getMessage());
        }
    }

    public void insertAccount(Account account) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(insertAccountSQL);
            stmt.setInt(1, account.getId());
            stmt.setDouble(2, account.getBalance());
            stmt.setInt(3, account.getHolder());
            stmt.setBoolean(3, account.isLocked());
            stmt.execute();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Insert account exception " + e.getMessage());
        }
    }
}
