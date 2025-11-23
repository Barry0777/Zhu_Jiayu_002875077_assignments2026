package business.model;

import business.model.people.Employee;
//@author Zhu Jiayu

public class Branch {
    private static int BRANCH_COUNTER = 1;
    private final int id;
    private String name;

    private Library library;
    private Employee manager;

    public Branch() { this("Branch"); }

    public Branch(String name) {
        this.id = BRANCH_COUNTER++;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }

    public Employee getManager() { return manager; }
    public void setManager(Employee manager) { this.manager = manager; }

    @Override
    public String toString() { return name + " (#" + id + ")"; }
}
