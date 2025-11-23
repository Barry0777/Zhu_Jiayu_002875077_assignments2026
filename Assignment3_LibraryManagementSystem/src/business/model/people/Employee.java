package business.model.people;

public class Employee extends Person {

    private static int seed = 1000;

    private final int staffId;
    private int years;

    public Employee() {
        this("Unnamed", 0);
    }

    public Employee(String name, int years) {
        super(name);
        this.staffId = seed++;
        this.years = years;
    }

    public int getEmployeeId() {
        return staffId;
    }

    public int getExperience() {
        return years;
    }

    public void setExperience(int val) {
        years = val;
    }
}
