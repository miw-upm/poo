package upm.exceptions;

import org.junit.jupiter.api.Test;
import upm.introduction.Age;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuntimeExceptionSnippetsTest {

    @Test
    void testIsAdult() {
        assertThrows(RuntimeException.class, () -> new RuntimeExceptionSnippets().methodPreconditions(-3) );
    }
}
