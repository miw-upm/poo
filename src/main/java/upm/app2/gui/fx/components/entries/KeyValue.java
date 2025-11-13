package upm.app2.gui.fx.components.entries;

public record KeyValue<T>(T key, String displayValue) {

    @Override
    public String toString() {
        return displayValue;
    }
}
