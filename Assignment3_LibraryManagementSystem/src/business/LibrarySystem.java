package business;

import business.directory.*;
//@author Zhu jiayu
public class LibrarySystem {

    private final UserAccountDirectory accounts;
    private final EmployeeDirectory employees;
    private final BranchDirectory branches;
    private final LibraryDirectory libraries;
    private final BookDirectory books;
    private final RentalDirectory rentals;
    private final AuthorDirectory authors;
    private final CustomerDirectory customers;

    public LibrarySystem() {
        accounts  = new UserAccountDirectory();
        employees = new EmployeeDirectory();
        branches  = new BranchDirectory();
        libraries = new LibraryDirectory();
        books     = new BookDirectory();
        rentals   = new RentalDirectory();
        authors   = new AuthorDirectory();
        customers = new CustomerDirectory();
    }

    public BranchDirectory getBranchDirectory() {
        return branches;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return accounts;
    }

    public RentalDirectory getRentalDirectory() {
        return rentals;
    }

    public BookDirectory getBookDirectory() {
        return books;
    }

    public AuthorDirectory getAuthorDirectory() {
        return authors;
    }

    public LibraryDirectory getLibraryDirectory() {
        return libraries;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employees;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customers;
    }
}
