package controller.strategies;

import model.Periodical;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByMin extends Sorting {
    public SortByMin(Comparator<Periodical> c) {
        this.comp = c;
    }
    @Override
    public ArrayList<Periodical> sort(ArrayList<Periodical> a) {
        a.sort(comp);
        Collections.reverse(a);
        return a;
    }
}
