package upm.inheritances.company;

public class Secretary {
    private final String name;
    private final String category;
    private Integer hours;

    public Secretary(String name, Integer hours, String category) {
        this.name = name;
        this.hours = hours;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public double salary() {
        if ("BASIC".equals(this.category)) {
            return this.hours * 12;
        } else if ("SPECIALIST".equals(this.category)) {
            return this.hours * 14;
        } else {
            return Double.NaN;
        }
    }

    public double retention() {
        return this.salary() * 0.19;
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "name='" + name + '\'' +
                ", hours=" + hours +
                ", category='" + category + '\'' +
                '}';
    }
}
