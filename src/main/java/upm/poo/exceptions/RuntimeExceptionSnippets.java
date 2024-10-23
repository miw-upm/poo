package upm.poo.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RuntimeExceptionSnippets {
    public final static double MINIMUM = 0;

    public static void main(String[] args) throws Exception {
        RuntimeExceptionSnippets app = new RuntimeExceptionSnippets();
        //app.throwRuntimeExceptionBySoftwareError();
        //app.throwRuntimeExceptionByInputError();
        //app.throwRuntimeExceptionByInputErrorWithTryCatch();
        //app.catchException();
        //app.throwsException();
        //app.throwException();

    }

    public void methodPreconditions(double value) {
        if (value < MINIMUM) {
            throw new IllegalArgumentException("value: " + value + ", must be greater than equal to zero.");
        }
    }

    public void throwRuntimeExceptionBySoftwareError() {
        int[] array = new int[2];
        for (int i = 0; i < 10; i++) {
            System.out.println("i: " + array[i]);
        }
    }

    public void throwRuntimeExceptionByInputError() {
        System.out.print(">");
        int input = new Scanner(System.in).nextInt();
        System.out.println("===>" + input);
    }

    public void throwRuntimeExceptionByInputErrorWithTryCatch() {
        System.out.print(">");
        try {
            int input = new Scanner(System.in).nextInt();
            System.out.println("===>" + input);
        } catch (InputMismatchException ime) {
            System.out.println("Excepción capturada (InputMismatchException): " + ime.getClass().getName());
        } catch (RuntimeException rte) {
            System.out.println("Excepción capturada (RuntimeException): " + rte.getClass().getName());
        } catch (Exception e) {
            System.out.println("Excepción capturada (Exception): " + e.getClass().getName());
        } finally {
            System.out.println("Finally se ejecuta siempre");
        }
        System.out.println("App sigue funcionando con normalidad...");
    }

    public void catchException() {
        try {
            FileReader fileReader = new FileReader("kk.txt");
        } catch (Exception e) {
            System.out.println("Excepción capturada: " + e.getClass().getName());
        }
    }

    public void throwsException() throws FileNotFoundException {
        FileReader fileReader = new FileReader("kk.txt");
    }

    public void throwRuntimeException() {
        throw new RuntimeException("Excepcion lanzada por mi código");
    }

    public void throwException() throws Exception {
        throw new Exception("Excepcion lanzada por mi código");
    }
}
