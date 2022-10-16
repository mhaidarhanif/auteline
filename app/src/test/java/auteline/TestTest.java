/*
 * This test just verifies that testing works :P
 */
package auteline;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTest {
    @Test
    public void testBasic() {
        // Arrange
        int one = 1;
        int two = 2;
        int EXPECTED = 3;

        // Act
        int actual = one + two;

        // Assert
        assertEquals(EXPECTED, actual);
    }
}
