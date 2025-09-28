package upm.app1.presentation.view;

import java.util.List;

import static upm.app1.presentation.view.AnsiCode.*;

public class View {

    private String line(String message) {
        return INDENT + message;
    }

    public void show(String message) {
        System.out.println(CYAN.apply(this.line(message)));
    }

    public void showImportant(String message) {
        System.out.println(PURPLE.apply(this.line(message), BOLD));
    }

    public void showError(String message) {
        System.out.println(BLACK.apply(FIRE + this.line(message), BACKGROUND_RED));
    }

    public void showCommandPrompt() {
        System.out.print(ITALIC.apply(COPY_RIGHT + "UPM", BOLD) + "shop> ");
    }

    public <T> void showItem(T item) {
        System.out.println(GREEN.apply(this.line(item.toString())));
    }

    public <T> void showList(String title, List<T> items) {
        if (items == null || items.isEmpty()) {
            this.show("No items available");
        } else {
            this.showImportant(title);
            int index = 1;
            for (T item : items) {
                System.out.println(GREEN.apply(this.line(index + ". " + item.toString())));
                index++;
            }
        }
    }

}
