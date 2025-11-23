package business.model.people;

public class Person {

    private static int seq = 1;

    private final int pid;
    private String label;

    public Person() {
        this("Unnamed");
    }

    public Person(String label) {
        this.pid = seq++;
        this.label = label;
    }

    public int getId() {
        return pid;
    }

    public String getName() {
        return label;
    }

    public void setName(String value) {
        this.label = value;
    }
}
