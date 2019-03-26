package view;

import XML.PeriodicalParserDOM;
import XML.XMLValidator;
import controller.PeriodicalBinding;
import controller.comparators.CompByCost;
import controller.comparators.CompByPeriod;
import controller.factories.PeriodicalFactory;
import controller.strategies.SortByMin;
import model.Period;
import model.Periodical;

import java.util.TreeSet;

/**
 * The class {@code Test} demonstrates capabilities of
 * {@code Periodical} classes hierarchy
 */

public class Test {
    public static void main(String[] args) {
        System.out.println(new XMLValidator().validate("input.xml", "periodical.xsd"));
        try {
            for(Periodical p : new PeriodicalParserDOM().parse("input.xml"))
                System.out.println(p);
        } catch (Exception e) {
            System.err.println("wrong");
        }
        PeriodicalFactory pf1 = PeriodicalFactory.getFactory("magazine");
        PeriodicalFactory pf2 = PeriodicalFactory.getFactory("newspaper");
        System.out.println("Example of working with abstract factories:");
        TreeSet<Periodical> post = new TreeSet<>(new CompByPeriod());

        post.add(pf1.createPrinted(2.4, Period.DAILY,"BelPrint", "SB",
                20, 10000, 122, 1000));
        post.add(pf2.createPrinted(4.1, Period.MONTHLY,"Eksmo", "Unbelievable",
                34, 5000, 10, 230));
        post.add(pf1.createOnline(1.1,"TUT", "www.tut.by",
                34453.2, 154, 82000));
        post.add(pf2.createOnline(5.1,"belpr", "www.pramen.by",
                31.4, 58, 820));

        for(Periodical p : post) {
            System.out.println(p);
        }

        Periodical[] arr = new Periodical[]{
                pf1.createPrinted(2.4, Period.DAILY,"BelPrint", "SB",
                        20, 10000, 122, 15000),
                pf2.createPrinted(3.7, Period.WEAKLY,"BelP", "Komsomol",
                        32, 10000, 122, 1000),
                pf1.createPrinted(1.4, Period.DAILY,"Ps", "AiF",
                        40, 10000, 122, 10000)};

        PeriodicalBinding pb = new PeriodicalBinding(arr, new SortByMin(new CompByCost()));
        System.out.println(pb);
        pb.sort();
        System.out.println(pb);
    }
}
