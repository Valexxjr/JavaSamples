package model.exception;

public class JDBCConnectionException extends Exception {
    /**
     * Empty constructor
     */
    public JDBCConnectionException(){
        super("JDBC connection exception");
    }

    /**
     * Constructor with message
     * @param message to explain
     */
    public JDBCConnectionException(String message){
        super(message);
    }
}
