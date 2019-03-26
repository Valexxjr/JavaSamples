package model;

import java.io.Serializable;

/**
 * The class {@code periodical} contains methods and fields for description
 * of standard properties of periodical
 * A root of Periodical hierarchy
 * @author Alexander Valai
 */
public abstract class Periodical implements Serializable{
    /** field contains the cost of periodical media */
    private double cost;
    /** field contains the period that illustrates frequency of appearing of new numbers*/
    protected Period period;
    /** the name of publishing house*/
    private String publishingHouse;

    public Period getPeriod() {
        return period;
    }

    public double getCost() {
        return cost;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public Periodical() {
        this.cost = 0;
        this.period = Period.WEAKLY;
        this.publishingHouse = null;
    }

    public Periodical(double cost, Period period, String publishingHouse) {
        this.cost = cost;
        this.period = period;
        this.publishingHouse = publishingHouse;
    }
}
