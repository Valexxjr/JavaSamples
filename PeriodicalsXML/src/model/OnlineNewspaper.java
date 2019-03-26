package model;

public class OnlineNewspaper extends OnlinePeriodical {
    private long subscribers;

    public OnlineNewspaper() {
        super();
        subscribers = 0;
    }

    public OnlineNewspaper(double cost, String publishingHouse, String website, double averageAttendance, long subscribers) {
        super(cost, publishingHouse, website, averageAttendance);
        this.subscribers = subscribers;
    }

    @Override
    public double priceForClick() {
        return getCost() * ((subscribers > 10000)? 1.5 : 1.3) / getAverageAttendance();
    }

    @Override
    public String toString() {
        return "Online Newspaper: " + "cost=" + getCost() + " period=" + getPeriod() + " publishingHouse=" +
                getPublishingHouse() + " averageAttendance=" + getAverageAttendance() + " total subscribers=" + subscribers + ";";
    }
}
