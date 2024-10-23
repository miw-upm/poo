package upm.poo.inheritances.company.solution;

public abstract class Staff {
    public static final double RETENTION_BASE = 0.19;
    private final String name;
    private Integer hours;

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getHours() {
        return this.hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public abstract double salary();

    public double retention() {
        return this.salary() * RETENTION_BASE;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", hours=" + hours +
                '}';
    }
}
