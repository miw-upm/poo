package upm.introduction;

public class Car {
    public static final double HORSEPOWER_TO_KILOWATTS = 0.7457;
    private final String model;
    private final int year;
    private final int horsepower;

    public Car(String model, int year, int horsepower) {
        this.model = model;
        this.year = year;
        this.horsepower = horsepower;
    }

    public static double convertHpToKilowatts(int hp) {
        return hp * Car.HORSEPOWER_TO_KILOWATTS;
    }

    public String getModel() {
        return this.model;
    }

    public int getYear() {
        return this.year;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", year=" + year +
                ", horsepower=" + horsepower +
                '}';
    }
}

