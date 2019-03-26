package controller.factories;

import model.Period;
import model.Periodical;

public abstract class PeriodicalFactory {
    enum periodicalType { NEWSPAPER, MAGAZINE }
    public static PeriodicalFactory getFactory(String input) {
        periodicalType pt = periodicalType.valueOf(input.toUpperCase());
        switch (pt) {
            case NEWSPAPER:
                return new NewspaperFactory();
            case MAGAZINE:
                return new MagazineFactory();
            default :
                throw new EnumConstantNotPresentException(periodicalType.class, pt.name());
        }
    }

    public abstract Periodical createPrinted(double cost, Period period, String publishingHouse, String name,
                                             int pagesNumber, int circulation, int adsNumber, long subscribers);
    public abstract Periodical createOnline(double cost, String publishingHouse, String website,
                                            double averageAttendance, int adsNumber, long subscribers);
}
