package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadingHall {
    private CopyOnWriteArrayList<Reader> readers = new CopyOnWriteArrayList<>();

    public void enterHall(Reader reader) {
        readers.add(reader);
    }

    public int getSize() {
        return readers.size();
    }

    public Reader findReceiver(Reader reader) {
        int size;
        if ((size = getSize()) > 1) {
            Random random = new Random();
            int index;
            Reader newReader = null;
            while ( (newReader = readers.get(index = random.nextInt(size))) == reader) {}
            return newReader;
        }
        return null;
    }
}
