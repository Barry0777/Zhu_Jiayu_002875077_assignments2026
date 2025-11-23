package business.model.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Author {

    private static int seq = 1;

    private final int authorId;
    private String fullName;
    private String nation;
    private String intro;
    private Integer born;
    private Integer died;

    private final List<Book> authoredList;

    public Author(String fullName) {
        this(fullName, null, null, null, null);
    }

    public Author(String fullName, String nation, String intro,
                  Integer born, Integer died) {
        this.authorId = seq++;
        authoredList = new ArrayList<>();
        setName(fullName);
        setNationality(nation);
        setBiography(intro);
        setBirthYear(born);
        setDeathYear(died);
    }

    public int getId() {
        return authorId;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty.");
        }
        fullName = value.trim();
    }

    public String getNationality() {
        return nation;
    }

    public void setNationality(String val) {
        nation = clean(val);
    }

    public String getBiography() {
        return intro;
    }

    public void setBiography(String val) {
        intro = clean(val);
    }

    public Integer getBirthYear() {
        return born;
    }

    public void setBirthYear(Integer val) {
        if (val != null && val < 0)
            throw new IllegalArgumentException("birthYear must be >= 0.");
        born = val;
        if (died != null && born != null && died < born)
            throw new IllegalArgumentException("deathYear must be >= birthYear.");
    }

    public Integer getDeathYear() {
        return died;
    }

    public void setDeathYear(Integer val) {
        if (val != null && val < 0)
            throw new IllegalArgumentException("deathYear must be >= 0.");
        if (val != null && born != null && val < born)
            throw new IllegalArgumentException("deathYear must be >= birthYear.");
        died = val;
    }

    public List<Book> getWorks() {
        return Collections.unmodifiableList(authoredList);
    }

    public void addBook(Book book) {
        if (book == null) return;
        if (!authoredList.contains(book)) {
            authoredList.add(book);
        }
        if (book.getAuthor() != this) {
            book.setAuthor(this);
        }
    }

    public void removeBook(Book book) {
        if (book == null) return;
        authoredList.remove(book);
        if (book.getAuthor() == this) {
            book.setAuthor(null);
        }
    }

    @Override
    public String toString() {
        return fullName + " (ID:" + authorId + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        return authorId == ((Author) o).authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId);
    }

    private static String clean(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}
