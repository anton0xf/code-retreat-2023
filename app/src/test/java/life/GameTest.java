package life;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void isAlive() {
        Game game = new Game(Set.of(new Point(3, 3)));
        assertTrue(game.isAlive(new Point(3, 3)));
    }

    @Test
    void neighboursOne() {
        Game game = new Game(Set.of(new Point(3, 3)));
        assertEquals(Set.of(new Point(3, 3)), game.neighbours(new Point(4, 3)));
    }

    @Test
    void neighboursTwo() {
        Game game = new Game(Set.of(new Point(3, 3), new Point(4, 4)));
        assertEquals(Set.of(new Point(3, 3), new Point(4, 4)),
                game.neighbours(new Point(4, 3)));
    }

    @Test
    void neighboursTwoWithoutSelf() {
        Game game = new Game(Set.of(new Point(3, 3), new Point(4, 4), new Point(4, 3)));
        assertEquals(Set.of(new Point(3, 3), new Point(4, 4)),
                game.neighbours(new Point(4, 3)));
    }

    @Test
    void countNeighboursOne() {
        Game game = new Game(Set.of(new Point(3, 3)));
        assertEquals(1, game.countNeighbours(new Point(4, 3)));
    }

    @Test
    void countNeighboursTwo() {
        Game game = new Game(Set.of(new Point(3, 3), new Point(4, 4)));
        assertEquals(2, game.countNeighbours(new Point(4, 3)));
    }

    @Test
    void notAliveNext() {
        Game game = new Game(Set.of(new Point(0, 1), new Point(1, 0)));
        assertFalse(game.isAliveNext(new Point(0, 0)));
    }

    @Test
    void notAliveNextAgain() {
        Game game = new Game(Set.of(new Point(0, 1), new Point(0, 0)));
        assertFalse(game.isAliveNext(new Point(0, 0)));
    }

    @Test
    void aliveNext() {
        Game game = new Game(Set.of(new Point(0, 1), new Point(1, 0), new Point(1, 1)));
        assertTrue(game.isAliveNext(new Point(0, 0)));
    }

    @Test
    void aliveNextAgain() {
        Game game = new Game(Set.of(new Point(0, 1), new Point(1, 0), new Point(1, 1), new Point(0, 0)));
        assertTrue(game.isAliveNext(new Point(0, 0)));
    }

    @Test
    void boundsOne() {
        Game game = new Game(Set.of(new Point(0, 1)));
        assertEquals(new Bounds(new Point(0, 1), new Point(0, 1)), game.bounds());
    }


    @Test
    void boundsTwo() {
        Game game = new Game(Set.of(new Point(0, 5), new Point(3, 1)));
        assertEquals(new Bounds(new Point(0, 1), new Point(3, 5)), game.bounds());
    }

    @Test
    void boundsEmpty() {
        Game game = new Game(Set.of());
        assertEquals(new Bounds(new Point(0, 0), new Point(0, 0)), game.bounds());
    }

    @Test
    void next() {
        Game game = new Game(Set.of(new Point(0, 1), new Point(1, 0), new Point(1, 1)));
        Game next = game.next();
        assertTrue(next.isAlive(new Point(0, 0)));
        assertTrue(next.isAlive(new Point(0, 1)));
        assertTrue(next.isAlive(new Point(1, 0)));
        assertTrue(next.isAlive(new Point(1, 1)));
        assertFalse(next.isAlive(new Point(1, 2)));
    }

    @Test
    void gameTest() {
        Game game = new Game(Set.of(new Point(0, 0), new Point(1, 1)));
        assertEquals("#_\n_#", game.showBoard());
    }
}