package life;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Game(Set<Point> field) {

    public static final Comparator<Integer> COMPARATOR = Comparator.comparing(Function.identity());

    public Bounds bounds() {
        if (field.isEmpty()) {
            return new Bounds(0, 0, 0, 0);
        }
        int minX = field.stream().map(Point::x).min(COMPARATOR).get();
        int maxX = field.stream().map(Point::x).max(COMPARATOR).get();
        int minY = field.stream().map(Point::y).min(COMPARATOR).get();
        int maxY = field.stream().map(Point::y).max(COMPARATOR).get();
        return new Bounds(minX, maxX, minY, maxY);
    }

    public String toString() {
        Bounds bounds = bounds();
        return IntStream.range(bounds.minY(), bounds.maxY() + 1)
                .mapToObj(y -> IntStream.range(bounds.minX(), bounds.maxX())
                        .mapToObj(x -> getChar(x, y)).collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));

    }

    private String getChar(int x, int y) {
        Point point = new Point(x, y);
        return field.contains(point) ? "#" : "_";
    }
}
