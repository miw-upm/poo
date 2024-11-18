package upm.doo.logs;

import org.apache.logging.log4j.LogManager;

public class Logs {
    public static void main(String[] args) {
        new Logs().printLogs();
    }

    public void printLogs() {
        LogManager.getLogger(this.getClass()).debug(() -> "debug example... ");
        LogManager.getLogger(this.getClass()).info(() -> "info example... ");
        LogManager.getLogger(this.getClass()).warn(() -> "warn example... ");
        LogManager.getLogger(this.getClass()).error(() -> "error example... ");
    }
}
