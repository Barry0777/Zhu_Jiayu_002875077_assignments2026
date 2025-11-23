package business.directory;

import business.model.people.Customer;
import java.util.*;
//@author Zhu jiayu

public class CustomerDirectory {
    private final List<Customer> list = new ArrayList<>();
    private final Map<String, Customer> byUsername = new HashMap<>();

    /** 按 username 去重创建；返回已存在或新建出的 Customer */
    public Customer create(String username, String fullName) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username required");
        }
        String key = username.toLowerCase();
        Customer existing = byUsername.get(key);
        if (existing != null) return existing;

        Customer c = new Customer(username, fullName);
        list.add(c);
        byUsername.put(key, c);
        return c;
    }

    public Customer findByUsername(String username) {
        if (username == null) return null;
        return byUsername.get(username.toLowerCase());
    }

    public List<Customer> getAll() {
        return Collections.unmodifiableList(list);
    }
}
