package controller.factories;

import model.*;

public class MagazineFactory extends PeriodicalFactory {

    @Override
    public Periodical createPrinted(double cost, Period period, String publishingHouse, String name, int pagesNumber, int circulation, int adsNumber, long subscribers) {
        return new Magazine(cost, period, publishingHouse, name, pagesNumber, circulation, adsNumber);
    }

    @Override
    public Periodical createOnline(double cost, String publishingHouse, String website, double averageAttendance, int adsNumber, long subscribers) {
        return new OnlineMagazine(cost, publishingHouse, website, averageAttendance, adsNumber);
    }
}
