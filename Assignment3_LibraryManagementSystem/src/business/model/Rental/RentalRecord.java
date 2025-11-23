/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.model.Rental;

import business.model.book.Book;
import java.time.LocalDate;

public class RentalRecord {
    private static int COUNTER = 1;
    private final int id; 
    private final Book book;
    private final String customerUsername;
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private RentalStatus status;

    public RentalRecord(Book book, String customerUsername, LocalDate start, LocalDate due){
        this.id = COUNTER++; 
        this.book = book;
        this.customerUsername = customerUsername;
        this.startDate = start;
        this.dueDate = due;
        this.status = RentalStatus.BORROWED;
    }

    public int getId() { return id; }
    public Book getBook(){ return book; }
    public String getCustomerUsername(){ return customerUsername; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getDueDate(){ return dueDate; }
    public LocalDate getReturnDate(){ return returnDate; }
    public void setReturnDate(LocalDate d){ this.returnDate = d; }
    public RentalStatus getStatus(){ return status; }
    public void setStatus(RentalStatus s){ this.status = s; }
    public boolean isActive() { return status == RentalStatus.BORROWED; }

}
