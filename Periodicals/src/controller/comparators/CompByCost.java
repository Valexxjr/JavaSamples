package controller.comparators;

import model.Periodical;

import java.io.Serializable;
import java.util.Comparator;

public class CompByCost implements Comparator<Periodical>, Serializable{

    @Override
    public int compare(Periodical o1, Periodical o2) {
        double c1 = o1.getCost();
        double c2 = o2.getCost();
        return (c1 > c2)? 1 : ((c1 == c2) ? 0 : (-1));
    }
}
