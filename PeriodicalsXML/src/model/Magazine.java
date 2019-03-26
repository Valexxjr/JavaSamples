package model;

/**
 * The class {@code Magazine} contains methods and fields for description
 * of standard properties of magazines
 * @author Alexander Valai
 */

public class Magazine extends PrintedPeriodical {
    private int adsNumber;

    public Magazine() {
        super();
        this.adsNumber = 0;
    }

    public Magazine(double cost, Period period, String publishingHouse,
                    String name, int pagesNumber, int circulation, int adsNumber) {
        super(cost, period, publishingHouse, name, pagesNumber, circulation);
        this.adsNumber = adsNumber;
    }

    @Override
    public double countAdPrice() {
        return 5.0 * adsNumber * getCost() / getPagesNumber();
    }

    @Override
    public double getSubscriptionCost() {
        switch (getPeriod()) {
            case ANNUAL:
                return getCost() * 0.95;
            case MONTHLY:
                return getCost() * 6 * 0.9;
            case WEAKLY:
                return getCost() * 52 * 0.8;
            case DAILY:
                return getCost() * 365 * 0.66;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "Printed magazine: " + "cost=" + getCost() + " period=" + getPeriod() + " publishingHouse=" +
                getPublishingHouse() + " name=" + getName() + " pagesNumber=" + getPagesNumber() +
                " circulation=" + getCirculation() + " number of ads=" + adsNumber + ";";
    }
}
