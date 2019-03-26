package model;

import controller.PeriodicalBinding;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface {@code RemoteControl}
 * includes functions that client can run from remote object
 * @author Alexander Valai
 */

public interface RemoteControl extends Remote {
    /**
     * method returns PeriodicalBinding object
     * */
    PeriodicalBinding getBinding() throws RemoteException;
}
