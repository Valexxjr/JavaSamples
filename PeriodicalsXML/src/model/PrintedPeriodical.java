package model;
/**
 * The class {@code periodical} contains methods and fields for description
 * of standard properties of printed periodical media
 * @author Alexander Valai
 */
public abstract class PrintedPeriodical extends Periodical {
    private String name;
    private int pagesNumber;
    private int circulation;

    public String getName() {
        return name;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public int getCirculation() {
        return circulation;
    }

    public PrintedPeriodical() {
        super();
        this.name = null;
        this.pagesNumber = pagesNumber;
        this.circulation = circulation;
    }

    public PrintedPeriodical(double cost, Period period, String publishingHouse,
                             String name, int pagesNumber, int circulation) {
        super(cost, period, publishingHouse);
        this.name = name;
        this.pagesNumber = pagesNumber;
        this.circulation = circulation;
    }

    public abstract double countAdPrice();
    public abstract double getSubscriptionCost();
}
