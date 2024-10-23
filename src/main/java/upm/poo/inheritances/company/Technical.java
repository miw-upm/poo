package upm.poo.inheritances.company;

public class Technical {
    private final String name;
    private final Integer level;
    private Integer hours;

    public Technical(String name, Integer hours, Integer level) {
        this.name = name;
        this.hours = hours;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getLevel() {
        return level;
    }

    public double salary() {
        return this.hours * this.level / 4;
    }

    public double retention() {
        return this.salary() * 0.19;
    }

    @Override
    public String toString() {
        return "Technical{" +
                "name='" + name + '\'' +
                ", hours=" + hours +
                ", level=" + level +
                '}';
    }
}
