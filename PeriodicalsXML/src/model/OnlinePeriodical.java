package model;
/**
 * The class {@code OnlinePeriodical} contains methods and fields for description
 * of standard properties of online periodical media
 * @author Alexander Valai
 */
public abstract class OnlinePeriodical extends Periodical {
    private String website;
    private double averageAttendance;

    public String getWebsite() {
        return website;
    }

    public double getAverageAttendance() {
        return averageAttendance;
    }

    public OnlinePeriodical() {
        super();
        this.website = null;
        this.averageAttendance = 0;
    }

    public OnlinePeriodical(double cost, String publishingHouse, String website, double averageAttendance) {
        super(cost, Period.REALTIME, publishingHouse);
        this.website = website;
        this.averageAttendance = averageAttendance;
    }

    public abstract double priceForClick();
}
