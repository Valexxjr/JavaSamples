package view;

import controller.Controller;
import model.Account;
import model.Client;
import model.Transfer;

import java.sql.Date;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();

        for(Client client: controller.getAllClients()) {
            System.out.println(client + ": ");
            for(Account account: controller.getAccountsByClient(client)) {
                System.out.println(account);
            }
            System.out.println("Accounts sum simple:" + controller.getClientAccountsSum(client));
            System.out.println("Accounts sum Criteria API:" + controller.getClientAccountsSumCriteria(client));
            System.out.println("-----");
        }

        for(Transfer transfer: controller.getAllTransfers()) {
            System.out.println(transfer);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 17);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        Date end = new Date(calendar.getTimeInMillis());

        for(Transfer transfer: controller.getAllTransfers()) {
            System.out.println(transfer);
        }

        controller.makePayment(controller.getAllAccounts().get(2), controller.getAllAccounts().get(3), 10);

        controller.makePaymentCriteria(controller.getAllAccounts().get(2), controller.getAllAccounts().get(3), 12);

        for(Transfer transfer: controller.getAllTransfers()) {
            System.out.println(transfer);
        }

        System.out.println("Period transfer sum ");
        System.out.println(controller.getPeriodTransfersSum(start, end, controller.getAllClients().get(1)));
        System.out.println("Period transfer sum criteria");
        System.out.println(controller.getPeriodTransfersSumCriteria(start, end, controller.getAllClients().get(1)));

        System.out.println("------------\nCriteriaApi");
        for(Account account: controller.getAllAccountsCriteria()) {
            System.out.println(account);
        }

        for(Account account: controller.getClientAccountsCriteria(controller.getAllClients().get(0))) {
            System.out.println(account);
        }

        controller.close();
    }
}
