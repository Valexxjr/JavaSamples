package controller.strategies;

import model.Periodical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Sorting implements Serializable{
    Comparator<Periodical> comp;
    public abstract ArrayList<Periodical> sort(ArrayList<Periodical> a);
}
