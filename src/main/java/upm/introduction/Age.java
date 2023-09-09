package upm.introduction;

public class Age {
    //TODO No cumple estandares de calidad!!!
    private int years;

    public Age(int years) {
        if (years >= 0) {
            this.years = years;
        }
    }

    public boolean isAdult() {
        return this.years >= 18; //A partir de 18 se considera adulto
    }

    public boolean isChild() {
        return this.years < 13;
    }

    public boolean isTeenager() {
        return !(this.years < 13) && !(this.years >= 18);
    }

    public int getYears() {
        return this.years;
    }

    public void setYears(int years) {
        if (years >= 0) {
            this.years = years;
        }
    }

}

