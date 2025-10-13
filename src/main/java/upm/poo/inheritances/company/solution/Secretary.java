package upm.poo.inheritances.company.solution;

public class Secretary extends Staff {
    private final Category category;

    public Secretary(String name, Category category) {
        super(name);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public double salary() {
        return (double) this.category.getBase() * this.getHours();
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "category=" + category +
                "} " + super.toString();
    }
}
