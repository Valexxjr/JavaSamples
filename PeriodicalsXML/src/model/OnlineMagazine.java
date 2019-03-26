package model;

public class OnlineMagazine extends OnlinePeriodical {
    private int adsNumber;

    public OnlineMagazine() {
        super();
        this.adsNumber = 0;
    }

    public OnlineMagazine(double cost, String publishingHouse, String website, double averageAttendance, int adsNumber) {
        super(cost, publishingHouse, website, averageAttendance);
        this.adsNumber = adsNumber;
    }

    @Override
    public double priceForClick() {
        return getCost() / (adsNumber * getAverageAttendance());
    }

    @Override
    public String toString() {
        return "Online Magazine:  " + "cost=" + getCost() + " period=" + getPeriod() + " publishingHouse=" +
                getPublishingHouse() + " averageAttendance=" + getAverageAttendance() + " number of ads=" + adsNumber + ";";
    }
}
