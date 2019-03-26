package model.DAOEntities;

import controller.ConnectionPool;
import model.Client;
import model.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOClient extends DAO {
    private static final String findAllSQL = "select id, name, surname from client";
    private static final String findByIdSQL = "select name, surname from client where id = ?";
    private static final String insertClientSQL = "insert into client (id, name, surname) values(?, ?, ?)";
    private static final String updateClientSQL = "update client set name = ?, surname = ? where id = ?";
    private static final String deleteClientSQL = "delete client where id = ?";

    public List<Client> findAll() throws DAOException {
        List<Client> ret = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt =  connection.prepareStatement(findAllSQL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Client client = new Client();
                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setSurname(rs.getString(3));
                ret.add(client);
            }
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Find all students exception " + e.getMessage());
        }
        return ret;
    }


    public void insertClient(Client client) throws DAOException {
        try {
            Connection connection = ConnectionPool.acquireConnection();
            PreparedStatement stmt = connection.prepareStatement(insertClientSQL);
            stmt.setInt(1, client.getId());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getSurname());
            stmt.execute();
            ConnectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DAOException("Insert client exception " + e.getMessage());
        }
    }
}
