package life;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundsTest {
    @Test
    void extend() {
        Bounds bounds = new Bounds(new Point(0, 0), new Point(0, 0));
        assertEquals(new Bounds(new Point(-1, -1), new Point(1, 1)), bounds.extend());
    }

    @Test
    void extendTwo() {
        Bounds bounds = new Bounds(new Point(1, 1), new Point(1, 1));
        assertEquals(new Bounds(new Point(0, 0), new Point(2, 2)), bounds.extend());
    }
}