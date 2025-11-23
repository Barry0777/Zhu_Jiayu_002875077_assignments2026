package business.model.people;

public class Customer {

    private static int seq = 1;

    private final int customerId;
    private String userKey;
    private String displayName;

    public Customer(String userKey, String displayName) {
        this.customerId = seq++;
        this.userKey = userKey;
        this.displayName = displayName;
    }

    public int getId() {
        return customerId;
    }

    public String getUsername() {
        return userKey;
    }

    public String getFullName() {
        return displayName;
    }

    public void setFullName(String name) {
        displayName = name;
    }

    @Override
    public String toString() {
        String base = displayName == null || displayName.isEmpty() ? userKey : displayName;
        return base + " (#" + customerId + ")";
    }
}
