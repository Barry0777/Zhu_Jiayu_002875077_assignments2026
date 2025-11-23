package business.model.book;

import business.model.Library;
import java.time.LocalDate;
import java.util.Objects;

/** 书籍实体（完整版，含 library 字段 & 正确的便捷构造） */
public class Book {

    // —— 自增主键 —— //
    private static int SERIAL_COUNTER = 1;

    // —— 字段 —— //
    private final int serial;           // 唯一编号
    private String name;                // 书名（必填）
    private LocalDate registeredDate;   // 入库日期（默认今天）
    private int pages;                  // 页数（>0）
    private String language;            // 语言（可选）
    private Author author;              // 作者（可空）
    private boolean rented = false;     // 是否借出
    private Library library;            // ★ 归属的馆（必需）

    // —— 构造 —— //
    public Book(String name,
                LocalDate registeredDate,
                int pages,
                String language,
                Author author,
                Library library) {
        this.serial = SERIAL_COUNTER++;
        setName(name);
        setRegisteredDate(registeredDate == null ? LocalDate.now() : registeredDate);
        setPages(pages);
        setLanguage(language);
        setAuthor(author);   // 若 Author 已实现双向维护，会把自己加入 author.works
        setLibrary(library);
    }

    /** 便捷构造：不传日期则用今天（★ 注意：补上 Library 参数） */
    public Book(String name, int pages, String language, Author author, Library library) {
        this(name, LocalDate.now(), pages, language, author, library);
    }

    // —— Getter / Setter —— //
    public int getSerial() { return serial; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Book name cannot be empty.");
        this.name = name.trim();
    }

    public LocalDate getRegisteredDate() { return registeredDate; }
    public void setRegisteredDate(LocalDate registeredDate) {
        if (registeredDate == null)
            throw new IllegalArgumentException("registeredDate cannot be null.");
        this.registeredDate = registeredDate;
    }

    public int getPages() { return pages; }
    public void setPages(int pages) {
        if (pages <= 0)
            throw new IllegalArgumentException("pages must be > 0.");
        this.pages = pages;
    }

    public String getLanguage() { return language; }
    public void setLanguage(String language) {
        this.language = (language == null ? null : language.trim());
    }

    public Author getAuthor() { return author; }
    /** 维护与 Author 的双向关系（Author 需有 addBook/removeBook/getWorks） */
    public void setAuthor(Author newAuthor) {
        if (this.author != null && this.author != newAuthor) {
            try { this.author.removeBook(this); } catch (Throwable ignore) {}
        }
        this.author = newAuthor;
        if (newAuthor != null) {
            try {
                java.util.List<Book> works = newAuthor.getWorks();
                if (works == null || !works.contains(this)) {
                    newAuthor.addBook(this);
                }
            } catch (Throwable ignore) {}
        }
    }

    public boolean isRented() { return rented; }
    public void setRented(boolean rented) { this.rented = rented; }

    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }
    
    public boolean isAvailable() { return !rented; }
    // —— 展示 & 对比 —— //
    @Override
    public String toString() {
        return name + " (#" + serial + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return serial == book.serial;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial);
    }
}
