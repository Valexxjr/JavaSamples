package controller;

import model.exception.JDBCConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final String path = "database.properties";
    private static BlockingQueue<Connection> connectionQueue;
    private static int connectionCount;

    /**
     * Create connection pool
     * @param connectionCount the size of pool
     * @throws JDBCConnectionException if problem during connection occurred
     */

    public static void initPool(int connectionCount) throws JDBCConnectionException {
        try {
            Properties propDB = new Properties();
            propDB.load(new FileInputStream(path));
            String JDBC_DRIVER = propDB.getProperty("driver");
            String DB_URL = propDB.getProperty("url");
            String USER = propDB.getProperty("user");
            String PASS = propDB.getProperty("password");
            Class.forName(JDBC_DRIVER);
            ConnectionPool.connectionCount = connectionCount;
            connectionQueue = new ArrayBlockingQueue<>(connectionCount);
            for (int i = 0; i < connectionCount; ++i) {
                connectionQueue.add(DriverManager.getConnection(DB_URL, USER, PASS));
            }
            System.out.println("DataBase class initialized");
            } catch (IOException e) {
            throw new JDBCConnectionException("Load properties file failed " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new JDBCConnectionException("A problem with driver occurred " + e.getMessage());
        } catch (SQLException e) {
            throw new JDBCConnectionException("Could not get connection " + e.getMessage());
        }
    }

    /**
     * Close connection pool
     * @throws JDBCConnectionException if problem during closing occurred
     */
    public static synchronized void stopPool() throws JDBCConnectionException {
        try {
            for(int i = 0; i < connectionCount; ++i)
                connectionQueue.take().close();
        } catch (SQLException e) {
            throw new JDBCConnectionException("Could not close database connection " + e.getMessage());
        } catch (InterruptedException e) {
            throw new JDBCConnectionException("Problem with inner concurrent utils found " + e.getMessage());
        }
    }

    /**
     * Peek connection from pool. The connection must be returned!!!
     * @return connection
     */
    public static synchronized Connection acquireConnection(){
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return the connection to pool
     * @param conn to return
     */
    public static synchronized void releaseConnection(Connection conn){
        connectionQueue.add(conn);
    }
}
