package upm.exceptions;

import org.junit.jupiter.api.Test;
import upm.poo.exceptions.RuntimeExceptionSnippets;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RuntimeExceptionSnippetsTest {

    @Test
    void testIsAdult() {
        assertThrows(RuntimeException.class, () -> new RuntimeExceptionSnippets().methodPreconditions(-3));
    }
}
