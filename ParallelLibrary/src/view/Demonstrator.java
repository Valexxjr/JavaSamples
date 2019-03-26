package view;

import controller.Library;
import controller.ReadingHall;
import controller.Reader;
import model.Book;
import model.BorrowType;
import org.apache.log4j.*;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Demonstrator {

    private static Library library;
    //private static Logger fileLogger = LogManager.getRootLogger();
    private static Logger consoleLogger = Logger.getLogger("logfile");

    public static void main(String[] args) {
        initLibrary();
        ReadingHall readingHall = new ReadingHall();
        Thread[] readers = new Thread[4];
        readers[0] = new Thread(new Reader("Maxim Valai       ", library, readingHall));
        readers[1] = new Thread(new Reader("Nastya Zagorskaya ", library, readingHall));
        readers[2] = new Thread(new Reader("Alexander Kuzmik   ", library, readingHall));
        readers[3] = new Thread(new Reader("Veronika Yastrebova ", library, readingHall));

        for (Thread t : readers) {
            t.start();
            consoleLogger.debug("Reader thread started");
        }
    }

    public static void initLibrary() {
        //fileLogger.debug("Program execution started\nLibrary initialisation started");
        consoleLogger.info("Library");
        library = new Library();
        CopyOnWriteArraySet<Book> lib = library.getLib();
        lib.add(new Book("100 years of loneliness", "Habriel Markes", BorrowType.TO_HOME));
        lib.add(new Book("Stainless steel rat", "Harrison Harry", BorrowType.READING_ROOM));
        lib.add(new Book("1984", "George Oruell", BorrowType.TO_HOME));
        lib.add(new Book("Strange new world", "Oldos Hacksley", BorrowType.TO_HOME));
        lib.add(new Book("Tom Sawyer", "Charles Dickens", BorrowType.TO_HOME));
        lib.add(new Book("Book thief", "Hans", BorrowType.READING_ROOM));
        lib.add(new Book("Alice in Wonderland", "Louis Carrol", BorrowType.TO_HOME));
        lib.add(new Book("Master and Margaret", "Michael Bulgakov", BorrowType.READING_ROOM));
        lib.add(new Book("People on Swamp", "Ivan Melezh", BorrowType.TO_HOME));
        lib.add(new Book("Da Vinci Code", "Dan Brown", BorrowType.TO_HOME));
        lib.add(new Book("A Farewell to Arms", "Ernest Hemingway", BorrowType.READING_ROOM));
        for(int i = 0; i < 20; i++) {
           lib.add(new Book("additional book " + (i+1), "Alexander Valai", BorrowType.TO_HOME));
        }
        //fileLogger.debug("Library initialisation finished");
    }
}
