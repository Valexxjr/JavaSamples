package controller.comparators;

import model.Periodical;

import java.io.Serializable;
import java.util.Comparator;

public class CompByPeriod implements Comparator<Periodical>, Serializable {

    @Override
    public int compare(Periodical o1, Periodical o2) {
        return o1.getPeriod().compareTo(o2.getPeriod());
    }

}
