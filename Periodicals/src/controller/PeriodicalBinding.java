package controller;

import controller.comparators.CompByCost;
import controller.strategies.SortByMax;
import controller.strategies.Sorting;
import model.Period;
import model.Periodical;

import java.io.Serializable;
import java.util.ArrayList;

public class PeriodicalBinding implements Serializable{
    private ArrayList<Periodical> array;
    private Sorting sorting;

    public ArrayList<Periodical> getArray() {
        return array;
    }

    public synchronized void setSorting(Sorting sorting) {
        this.sorting = sorting;
    }

    public PeriodicalBinding() {
        array = new ArrayList<>();
        sorting = new SortByMax(new CompByCost());
    }

    public PeriodicalBinding(Periodical[] arr, Sorting sorting) {
        this.array = new ArrayList<Periodical>();
        for(Periodical periodical: arr) {
            this.array.add(periodical);
        }
        this.sorting = sorting;
    }

    public void sort() {
        array = sorting.sort(array);
    }

    public Periodical[] find(double cost, Period period) {
        ArrayList<Periodical> res = new ArrayList<>();
        for(Periodical periodical: array) {
            if(Math.abs(cost - periodical.getCost()) < 1e-2 && period.equals(periodical.getPeriod()))
                res.add(periodical);
        }
        Periodical[] result = new Periodical[res.size()];
        return result;
    }

    public ArrayList<Periodical> find(Period period) {
        ArrayList<Periodical> res = new ArrayList<>();
        for(Periodical periodical: array) {
            if(period.equals(periodical.getPeriod()))
                res.add(periodical);
        }
        return res;
    }

    public ArrayList<Periodical> find(double cost) {
        ArrayList<Periodical> res = new ArrayList<>();
        for(Periodical periodical: array) {
            if(Math.abs(cost - periodical.getCost()) < 1e-2)
                res.add(periodical);
        }

        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PeriodicalBinding: ");
        for(Periodical p: array) {
            sb.append(p + "\n");
        }
        return sb.toString();
    }
}