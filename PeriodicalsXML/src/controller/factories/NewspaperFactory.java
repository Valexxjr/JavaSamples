package controller.factories;

import model.*;

public class NewspaperFactory extends PeriodicalFactory {

    @Override
    public Periodical createPrinted(double cost, Period period, String publishingHouse, String name, int pagesNumber, int circulation, int adsNumber, long subscribers) {
        return new Newspaper(cost, period, publishingHouse, name, pagesNumber, circulation, subscribers);
    }

    @Override
    public Periodical createOnline(double cost, String publishingHouse, String website, double averageAttendance, int adsNumber, long subscribers) {
        return new OnlineNewspaper(cost, publishingHouse, website, averageAttendance, subscribers);
    }
}
