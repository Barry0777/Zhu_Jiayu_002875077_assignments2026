package business.security;

public class UserAccount {

    private final String user;
    private final String pass;
    private final Role assignedRole;

    private Integer linkedLibrary;

    public UserAccount(String user, String pass, Role assignedRole) {
        this.user = user;
        this.pass = pass;
        this.assignedRole = assignedRole;
    }

    public String getUsername() {
        return user;
    }

    public Role getRole() {
        return assignedRole;
    }

    public Integer getLibraryId() {
        return linkedLibrary;
    }

    public void setLibraryId(Integer val) {
        linkedLibrary = val;
    }

    public boolean authenticate(String u, String p) {
        return user.equals(u) && pass.equals(p);
    }
}
