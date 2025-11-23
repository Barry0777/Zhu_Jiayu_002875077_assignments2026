package business.model;

import business.directory.AuthorDirectory;
import business.directory.BookDirectory;
import business.directory.RentalDirectory;
import business.model.people.Employee;

public class Library {

    private static int seq = 1;

    private final int libraryId;
    private String code;

    private final AuthorDirectory authors;
    private final BookDirectory books;
    private final RentalDirectory rentals;

    private Employee assignedManager;

    public Library() {
        this("Unit");
    }

    public Library(String code) {
        this.libraryId = seq++;
        this.code = code;
        this.authors = new AuthorDirectory();
        this.books = new BookDirectory();
        this.rentals = new RentalDirectory();
    }

    public int getId() {
        return libraryId;
    }

    public String getBuildingNo() {
        return code;
    }

    public void setBuildingNo(String val) {
        this.code = val;
    }

    public AuthorDirectory authorDir() {
        return authors;
    }

    public BookDirectory bookDir() {
        return books;
    }

    public RentalDirectory rentalDir() {
        return rentals;
    }

    public Employee getManager() {
        return assignedManager;
    }

    public void setManager(Employee emp) {
        this.assignedManager = emp;
    }

    @Override
    public String toString() {
        return code + " (ID:" + libraryId + ")";
    }
}
