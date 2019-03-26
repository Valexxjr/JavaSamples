package controller;

import model.Book;
import model.BorrowType;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Reader is Runnable so it's the thread class
 * */

public class Reader implements Runnable {
    /**
     * All the readers have access to the library
     * */
    private Library library;
    /**
     * All the readers have access to the reading hall
     * */
    private ReadingHall readingHall;
    private ArrayList<Book> books;
    private String name;
    private Random rand = new Random();
    private boolean inHall = false;


    public Reader(String name, Library library, ReadingHall readingHall) {
        this.name = name;
        this.library = library;
        this.readingHall = readingHall;
        books = new ArrayList<>();
    }

    public boolean takeBook(Book book, boolean toHome) {
        if(toHome) {
            if(book.getBorrowType() == BorrowType.TO_HOME) {
                books.add(book);
                return true;
            }
        }
        else {
            if(!inHall) {
                readingHall.enterHall(this);
                inHall = true;
            }
                books.add(book);
            return true;
        }
        return false;
    }

    public Book returnBook() {
        int index = rand.nextInt(books.size());
        Book book = books.get(index);
        books.remove(index);
        return book;
    }

    public Book exchangeBook(Reader reader) {
        Book book = returnBook();
        reader.takeBook(book, false);
        return book;
    }

    public void takeBookFromLibrary() {
        if(rand.nextInt(100) % 5 > 0) {
            Book book = library.takeBook();
            takeBook(book, false);
            System.out.println(name + " get book to reading room: " + book);
        }
        else {
            Book book = library.takeBook();
            if(takeBook(book, true)) {
                System.out.println(name + " get book to home: " + book);
            }
            else {
                System.out.println(name + " tried to get book to home, but it's not allowed: " + book);
                library.bringBook(book);
            }
        }
    }

    public void exchangeBookWithReader() {
        if(books.size() > 0) {
            if(readingHall.getSize() > 1) {
                Reader receiver = readingHall.findReceiver(this);
                if(receiver != null) {
                    System.out.println(name + " gave to " + receiver.name + " book: " + exchangeBook(receiver));
                }
            }
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < 30; i++) {
            switch (rand.nextInt(100) % 5) {
                case 0:
                    takeBookFromLibrary();
                    break;
                case 1:
                    exchangeBookWithReader();
                    break;
                case 2:
                    exchangeBookWithReader();
                    break;
                default:
                    if(books.size() > 0) {
                        Book book = returnBook();
                        library.bringBook(book);
                        System.out.println(name + " took book: " + book);
                    }
                    else {
                        //System.out.println(name + " has no books to took.");
                    }
                    break;
            }
        }

    }
}
