package es.upm.poo;

import es.upm.poo.configuration.DependencyInjector;

public class App {

    public static void main(String[] args) {
        DependencyInjector.getInstance().run();
    }
}
