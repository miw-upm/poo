package upm.app2.gui.fx.components.entries;

public interface EntryField {
    String value();

    default void clear() {
        // Default, nothing to clear
    }
}
