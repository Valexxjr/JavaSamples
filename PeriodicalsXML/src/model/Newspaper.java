package model;

/**
 * The class {@code NewsPaper} contains methods and fields for description
 * of standard properties of newspapers
 * @author Alexander Valai
 */

public class Newspaper extends PrintedPeriodical {
    private long subscribers;

    public Newspaper() {
        super();
        this.subscribers = 0;
    }

    public Newspaper(double cost, Period period, String publishingHouse,
                     String name, int pagesNumber, int circulation, long subscribers) {
        super(cost, period, publishingHouse, name, pagesNumber, circulation);
        this.subscribers = subscribers;
    }

    @Override
    public double countAdPrice() {
        return ((subscribers > 100000) ? 2.0 : 1.5) * getCost() / getCirculation();
    }

    @Override
    public double getSubscriptionCost() {
        switch (getPeriod()) {
            case ANNUAL:
                return getCost() * 0.95;
            case MONTHLY:
                return getCost() * 6 * 0.9;
            case WEAKLY:
                return getCost() * 52 * 0.88;
            case DAILY:
                return getCost() * 365 * 0.73;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "Printed newspaper:" + "cost=" + getCost() + " period=" + getPeriod() + " publishingHouse=" +
                getPublishingHouse() + " name=" + getName() + " pagesNumber=" + getPagesNumber() +
                " circulation=" + getCirculation() + " total subscribers=" + subscribers + ";";
    }
}
