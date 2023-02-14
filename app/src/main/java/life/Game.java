package life;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Game(Set<Point> points) {
    boolean isAlive(Point point) {
        return points.contains(point);
    }

    boolean isAliveNext(Point point) {
        int count = countNeighbours(point);
        return isAlive(point) ? count == 3 || count == 2 : count == 3;
    }

    Set<Point> neighbours(Point point) {
        return IntStream.range(point.x() - 1, point.x() + 2).boxed()
                .flatMap(x -> IntStream.range(point.y() - 1, point.y() + 2)
                        .mapToObj(y -> new Point(x, y)))
                .filter(p -> !p.equals(point))
                .filter(this::isAlive)
                .collect(Collectors.toSet());
    }

    int countNeighbours(Point point) {
        return (int) neighbours(point).stream()
                .filter(this::isAlive).count();
    }

    Bounds bounds() {
        if (points.isEmpty()) {
            return new Bounds(new Point(0, 0), new Point(0, 0));
        }
        return new Bounds(
                new Point(
                        points.stream().map(p -> p.x()).min(Comparator.naturalOrder()).get(),
                        points.stream().map(p -> p.y()).min(Comparator.naturalOrder()).get()
                ),
                new Point(
                        points.stream().map(p -> p.x()).max(Comparator.naturalOrder()).get(),
                        points.stream().map(p -> p.y()).max(Comparator.naturalOrder()).get()));
    }

    Game next() {
        Bounds bounds = bounds().extend();

        Set<Point> ps = IntStream.range(bounds.min().x(), bounds.max().x() + 1).boxed()
                .flatMap(x -> IntStream.range(bounds.min().y(), bounds.max().y() + 1)
                        .mapToObj(y -> new Point(x, y)))
                .filter(this::isAliveNext)
                .collect(Collectors.toSet());

        return new Game(ps);
    }

    String showBoard() {
        Bounds bounds = bounds();

        return IntStream.range(bounds.min().x(), bounds.max().x() + 1).boxed()
                .map(x -> IntStream.range(bounds.min().y(), bounds.max().y() + 1)
                        .mapToObj(y -> new Point(x, y))
                        .map(this::isAlive)
                        .map(alive -> alive ? "#" : "_")
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}
