// src/business/directory/BookDirectory.java
package business.directory;

import business.model.book.Author;
import business.model.book.Book;
import business.model.Library;
import java.time.LocalDate;
import java.util.*;
//@author Zhu jiayu
public class BookDirectory {
    private final List<Book> list = new ArrayList<>();

    public Book create(String name, LocalDate registeredDate, int pages,
                       String language, Author author, Library library) {
        Book b = new Book(name, registeredDate, pages, language, author, library);
        list.add(b);
        return b;
    }

    public List<Book> getAll() { return Collections.unmodifiableList(list); }
}
