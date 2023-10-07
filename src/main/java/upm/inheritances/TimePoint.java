package upm.inheritances;

public class TimePoint extends Point {
    private int seconds;

    public TimePoint(int x, int y, int seconds) {
        super(x, y);
        this.seconds = seconds;
    }

    public int getTime() {
        return this.seconds;
    }

    public void setTime(int time) {
        this.seconds = time;
    }

    public double velocity() {
        return this.module() / this.seconds;
    }

    @Override
    public String toString() {
        return super.toString() + "TimePoint{seconds=" + seconds + '}';
    }
}
