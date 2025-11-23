// src/business/directory/AuthorDirectory.java
package business.directory;

import business.model.book.Author;
import java.util.*;
//@author Zhu jiayu
public class AuthorDirectory {
    private final List<Author> list = new ArrayList<>();

    public Author create(String name) {
        Author a = new Author(name);
        list.add(a);
        return a;
    }

    public List<Author> getAll() {
        return Collections.unmodifiableList(list);
    }

    // 可选：按名查找
    public Author findByName(String name) {
        if (name == null) return null;
        for (Author a : list) {
            if (name.equalsIgnoreCase(a.getName())) return a;
        }
        return null;
    }
}
