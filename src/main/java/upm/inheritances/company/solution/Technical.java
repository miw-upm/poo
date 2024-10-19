package upm.inheritances.company.solution;

public class Technical extends Staff {
    public static final double BASE = 0.25;
    private final Integer level;

    public Technical(String name, Integer level) {
        super(name);
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public double salary() {
        return this.getHours() * this.level * BASE;
    }

    @Override
    public String toString() {
        return "Technical{" +
                "level=" + level +
                "} " + super.toString();
    }
}
