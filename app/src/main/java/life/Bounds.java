package life;

public record Bounds(Point min, Point max) {
    Bounds extend(){
        return new Bounds(
                new Point(min.x() - 1, min.y() - 1),
                new Point(max.x() + 1, max.y() + 1));
    }
}
