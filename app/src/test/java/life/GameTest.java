package life;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void boundsOfOne() {
        Game game = new Game(Set.of(new Point(1, 2)));
        assertEquals(new Bounds(1, 1, 2, 2), game.bounds());
    }

    @Test
    void boundsOfEmpty() {
        Game game = new Game(Set.of());
        assertEquals(new Bounds(0, 0, 0, 0), game.bounds());
    }

    @Test
    void boundsOfTwo() {
        Game game = new Game(Set.of(new Point(1, 2), new Point(-1, 5)));
        assertEquals(new Bounds(-1, 1, 2, 5), game.bounds());
    }

    @Test
    void toStringOfTwo() {
        Game game = new Game(Set.of(new Point(1, 0), new Point(-1, 2)));
        assertEquals("__#\n#__", game.toString());
    }
}