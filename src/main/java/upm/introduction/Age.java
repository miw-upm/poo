package upm.introduction;

public class Age {
    private static final int MINIMO = 0;
    private static final int ADULTO = 18;
    private int years;

    public Age(int years) {
        this.setYears(years);
    }

    public Age() {
        this(0);
    }

    public boolean isAdult() {
        return this.years >= ADULTO;
    }

    public boolean isChild() {
        return this.years < 13;
    }

    public boolean isTeenager() {
        return !(this.years < 13) && !(this.years >= 18);
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        if (years < MINIMO) {
            years = MINIMO;
        }
        this.years = years;
    }

    @Override
    public String toString() {
        return "Age{" +
                "years=" + years +
                '}';
    }
}

