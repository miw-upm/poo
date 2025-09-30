package upm.app1.presentation.view;

import upm.introduction.Point;

import java.util.List;

public class Snippets {
    public static void main(String[] args) {
        System.out.println(AnsiCode.BLACK.apply("Prueba de color", AnsiCode.BACKGROUND_WHITE, AnsiCode.STRIKETHROUGH, AnsiCode.BOLD));
        View view = new View();
        view.show("Mensaje Normal");
        view.showImportant("Mensaje resaltado");
        view.showTitle("TITLE");
        view.showError("Mensaje de error");
        view.showItem(new Point(3, 5));
        view.showList("Lista de frutas", List.of("Plátano", "Naranja", "Manzana"));
        view.showCommandPrompt();
    }
}
