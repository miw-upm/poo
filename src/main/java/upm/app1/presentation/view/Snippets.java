package upm.app1.presentation.view;

import upm.introduction.Point;

import java.util.List;

public class Snippets {
    public static void main(String[] args) {
        View view = new View();
        view.show("Mensaje Normal");
        view.showImportant("Mensaje resaltado");
        view.showError("Mensaje de error");
        view.showItem(new Point(3, 5));
        view.showList("Lista de frutas", List.of("Plátano", "Naranja", "Manzana"));
        view.showCommandPrompt();
    }
}
