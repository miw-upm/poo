package upm.poo.inheritances.company.solution;

public enum Category {
    BASIC(12), SPECIALIST(14);

    private final int base;

    Category(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }
}
