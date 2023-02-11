package life;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldReturnIsAlive() {
        Point p = new Point(0, 0);
        Game game = new Game(Set.of(p), 1);
        assertTrue(game.isAlive(p));
    }

    @Test
    void shouldReturnIsAliveOutOfBoundsX() {
        Point p = new Point(-1, 0);
        Game game = new Game(Set.of(p), 1);
        assertFalse(game.isAlive(p));
    }
    @Test
    void shouldReturnIsAliveOutOfBoundsY() {
        Point p = new Point(0, 1);
        Game game = new Game(Set.of(p), 1);
        assertFalse(game.isAlive(p));
    }

    @Test
    void countNeighbours () {
        Game game = new Game(Set.of(new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1)),
                2);
        assertEquals(3, game.countNeighbours(new Point(0, 0)));
    }

    @Test
    void newLive () {
        Game game = new Game(Set.of(new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1)),
                2);
        assertTrue(game.isAliveNext(new Point(0, 0)));
    }

    @Test
    void continueLife () {
        Game game = new Game(Set.of(new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1)),
                2);
        assertTrue(game.isAliveNext(new Point(0, 0)));
    }

    @Test
    void nextGame () {
        Game game = new Game(Set.of(new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1)),
                2);
        Game next = game.nextGame();
        assertTrue(next.isAlive(new Point(0, 0)));
        assertTrue(next.isAlive(new Point(0, 1)));
        assertTrue(next.isAlive(new Point(1, 0)));
        assertTrue(next.isAlive(new Point(1, 1)));
    }

    @Test
    void nextGame2 () {
        Game game = new Game(Set.of(new Point(0, 0),
                new Point(0, 1)),
                2);
        Game next = game.nextGame();
        assertFalse(next.isAlive(new Point(0, 0)));
        assertFalse(next.isAlive(new Point(0, 1)));
        assertFalse(next.isAlive(new Point(1, 0)));
        assertFalse(next.isAlive(new Point(1, 1)));
    }
}