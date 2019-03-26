package view;

import controller.ServerPeriodicalController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * The class {@code Server}
 * starts sever and exports
 * @author Alexander Valai
 */

public class Server {

    private static Logger fileLogger = LogManager.getRootLogger();
    private static Logger consoleLogger = Logger.getLogger("logfile");

    public static void main(String[] args) {
        final ServerPeriodicalController serverController = new ServerPeriodicalController();
        try {
            consoleLogger.warn("Server started");
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            Remote stub = UnicastRemoteObject.exportObject(serverController, 0);
            final Registry registry = LocateRegistry.createRegistry(2099);
            registry.bind("server.remoteControl", stub);
            consoleLogger.warn("registry bind");
        }
        catch (RemoteException re) {
            System.err.println(re.getMessage());
            fileLogger.error(re.getMessage());
        }
        catch (AlreadyBoundException ae) {
            System.err.println(ae.getMessage());
            fileLogger.error(ae.getMessage());
        }
    }
}
