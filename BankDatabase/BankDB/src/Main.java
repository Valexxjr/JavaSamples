import controller.ConnectionPool;
import controller.Controller;
import model.Account;
import model.Client;
import model.DAOEntities.DAOAccount;
import model.DAOEntities.DAOClient;
import model.DAOEntities.DAOTransfer;
import model.exception.DAOException;
import model.exception.JDBCConnectionException;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;


/**
 * The main class
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Loading driver...");
        try {
            ConnectionPool.initPool(10);
            DAOClient daoClient = new DAOClient();

            List<Client> list = daoClient.findAll();
            List<Account> accounts = new DAOAccount().findAll();

            Controller controller = new Controller();
            controller.printAllClients();
            controller.printAllAccounts();
            System.out.println();

            controller.printAllAccounts(list.get(1));

            controller.allAccountsSum(list.get(1));
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2018);
            calendar.set(Calendar.MONTH, Calendar.JULY);
            calendar.set(Calendar.DAY_OF_MONTH, 17);
            Date start = new Date(calendar.getTimeInMillis());
            calendar.set(Calendar.YEAR, 2019);
            calendar.set(Calendar.MONTH, Calendar.OCTOBER);
            calendar.set(Calendar.DAY_OF_MONTH, 10);
            Date end = new Date(calendar.getTimeInMillis());

            controller.periodPaimentsSum(list.get(1), start, end);

            Account sender = accounts.get(2);
            Account receiver = accounts.get(1);
            System.out.println("Sender: " + sender);
            System.out.println("Receiver: " + receiver);

            new DAOTransfer().makePaiment(sender, receiver, 14.88);


        } catch (DAOException e) {
            e.printStackTrace();
        } catch (JDBCConnectionException e){
            System.err.println(e.getMessage());
        }
        finally {
            try {
                ConnectionPool.stopPool();
            } catch (JDBCConnectionException e) {
                e.printStackTrace();
            }
        }
    }

}