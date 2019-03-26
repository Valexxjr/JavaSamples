package controller;

import model.Book;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The library class realises synchronised access to the resources
 * */

public class Library {

    private CopyOnWriteArraySet<Book> lib = new CopyOnWriteArraySet<>();

    public CopyOnWriteArraySet<Book> getLib() {
        return lib;
    }

    public Book takeBook() {
        Book book = null;
        if(lib.size() > 1) {
            book = lib.iterator().next();
            lib.remove(book);
        }
        return book;
    }

    public Book takeBook(Book book) {
        if(lib.contains(book)) {
            lib.remove(book);
            return book;
        }
        return null;
    }

    public void bringBook(Book book) {
        lib.add(book);
    }
}
