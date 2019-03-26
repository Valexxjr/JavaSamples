package controller;

import controller.comparators.CompByCost;
import controller.factories.PeriodicalFactory;
import controller.strategies.SortByMax;
import model.Period;
import model.Periodical;
import model.RemoteControl;

import java.rmi.RemoteException;
import java.util.ArrayList;



public class ServerPeriodicalController implements RemoteControl {
    private PeriodicalBinding periodicalBinding;

    public ServerPeriodicalController() {
        initBinding();
    }

    @Override
    public PeriodicalBinding getBinding() throws RemoteException {
        return periodicalBinding;
    }

    private void initBinding() {
        PeriodicalFactory pf1 = PeriodicalFactory.getFactory("magazine");
        PeriodicalFactory pf2 = PeriodicalFactory.getFactory("newspaper");
        ArrayList<Periodical> ap = new ArrayList<>();
        ap.add(pf1.createPrinted(2.4, Period.DAILY,"BelPrint", "SB",
                20, 10000, 122, 1000));
        ap.add(pf2.createPrinted(4.1, Period.MONTHLY,"Eksmo", "Unbelievable",
                34, 5000, 10, 230));
        ap.add(pf1.createOnline(1.1,"TUT", "www.tut.by",
                34453.2, 154, 82000));
        ap.add(pf2.createOnline(5.1,"ДАЛС", "www.dals.com",
                11.8, 14, 50));
        ap.add(pf1.createPrinted(7.59, Period.DAILY,"MAIL", "Daily Mail",
                20, 100000, 582, 50000));
        ap.add(pf2.createPrinted(8.0, Period.MONTHLY,"BritPr", "The Guardian",
                80, 150000, 284, 150500));
        ap.add(pf2.createPrinted(8.94, Period.ANNUAL,"bell", "AnnualCalendar",
                370, 990000, 16, 0));
        ap.add(pf1.createOnline(0.89,"belat", "www.naviny.by",
                3445.1, 275, 8000));
        ap.add(pf2.createOnline(0.17,"mail.ru", "www.mail.ru",
                15123.9, 1230, 1434529));

        Periodical[] arr = new Periodical[ap.size()];
        for(int i = 0; i < ap.size(); i++) {
            arr[i] = ap.get(i);
        }
        periodicalBinding = new PeriodicalBinding(arr, new SortByMax(new CompByCost()));
    }

}
