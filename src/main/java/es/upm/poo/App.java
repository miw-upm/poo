package es.upm.poo;

import es.upm.poo.configuration.CliDependencyInjector;

public class App {

    public static void main(String[] args) {
        CliDependencyInjector.getInstance().run();
    }
}
