package business.directory;

import business.model.Library;
import business.model.book.Book;
import business.model.people.Customer;
import business.model.Rental.RentalRecord;
import business.model.Rental.RentalStatus;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//@author Zhu jiayu
public class RentalDirectory {
    private final List<RentalRecord> list = new ArrayList<>();

    public RentalRecord create(Book book, String username, LocalDate start, LocalDate due){
        RentalRecord r = new RentalRecord(book, username, start, due);
        list.add(r);
        // ★ 同时把书标记为已借出
        book.setRented(true);
        return r;
    }

    public List<RentalRecord> getAll() { return list; }

    // ★ 新增：查某个用户的所有租借记录
    public List<RentalRecord> findByUsername(String username) {
        if (username == null) return List.of();
        String key = username.toLowerCase();
        return list.stream()
                .filter(x -> x.getCustomerUsername().equalsIgnoreCase(key))
                .collect(Collectors.toList());
    }

    // ★ 新增：查某个用户当前借着的书（未归还）
    public List<RentalRecord> findActiveByUsername(String username) {
        if (username == null) return List.of();
        String key = username.toLowerCase();
        return list.stream()
                .filter(x -> x.getCustomerUsername().equalsIgnoreCase(key)
                          && x.getStatus() == RentalStatus.BORROWED)
                .collect(Collectors.toList());
    }

    // ★ 新增：归还一个记录（Customer Return 里会调用）
    public void returnRental(RentalRecord record, LocalDate returnDate) {
        if (record == null) return;
        record.setReturnDate(returnDate);
        record.setStatus(RentalStatus.RETURNED);
        record.getBook().setRented(false);
    }
}
