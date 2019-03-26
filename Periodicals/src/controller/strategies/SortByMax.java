package controller.strategies;

import model.Period;
import model.Periodical;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByMax extends Sorting {
    public SortByMax(Comparator<Periodical> c) {
        this.comp = c;
    }
    @Override
    public ArrayList<Periodical> sort(ArrayList<Periodical> a) {
        a.sort(comp);
        return a;
    }
}
