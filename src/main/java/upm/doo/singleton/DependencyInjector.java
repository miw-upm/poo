package upm.doo.singleton;

public class DependencyInjector {
    private static DependencyInjector instance = new DependencyInjector();

    private DependencyInjector() {
        //TODO
    }

    public static DependencyInjector getInstance() {
        return DependencyInjector.instance;
    }

    public void run(){
        //TODO
    }
}
