package model;

public class Book {
    private String name;
    private String author;
    private BorrowType borrowType;

    public Book(String name, String author, BorrowType borrowType) {
        this.name = name;
        this.author = author;
        this.borrowType = borrowType;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public BorrowType getBorrowType() {
        return borrowType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return borrowType == book.borrowType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (borrowType != null ? borrowType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", borrowType=" + borrowType + '}';
    }
}
