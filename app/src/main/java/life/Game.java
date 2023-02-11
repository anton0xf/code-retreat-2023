package life;

import org.checkerframework.common.value.qual.IntRange;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
    private final int n;
    private final boolean[][] filed;

    public Game(Set<Point> points, int n) {
        this.n = n;
        filed = new boolean[n][n];
        for (Point point : points) {
            if (outOfBounds(n, point)) {
                continue;
            }
            filed[point.x()][point.y()] = true;
        }
    }

    private static boolean outOfBounds(int n, Point point) {
        return point.x() < 0 || point.y() < 0
                || point.x() >= n || point.y() >= n;
    }

    boolean isAlive(Point point) {
        if (outOfBounds(n, point)) {
            return false;
        }
        return filed[point.x()][point.y()];
    }

    int countNeighbours(Point point) {
        return (int) IntStream
                .range(point.y() - 1, point.y() + 2)
                .boxed()
                .flatMap(y -> IntStream.range(point.x() - 1, point.x() + 2).mapToObj(x -> new Point(x, y)))
                .filter(p -> p.x() != point.x() || p.y() != point.y())
                .filter(this::isAlive)
                .count();
    }

    boolean isAliveNext(Point point) {
        int m = countNeighbours(point);
        if (isAlive(point)) {
            return m == 3 || m == 2;
        } else {
            return m == 3;
        }
    }

    Game nextGame() {
        Set<Point> points = IntStream.range(0, n).boxed()
                .flatMap(y -> IntStream.range(0, n).mapToObj(x -> new Point(x, y)))
                .filter(this::isAliveNext)
                .collect(Collectors.toSet());
        return  new Game(points, n);
    }
}
