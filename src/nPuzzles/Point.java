package nPuzzles;

public class Point {
    private int x, y;

    // Constructor
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    // Setter
    Point set (int x, int y) {
        return new Point (x, y);
    }
}
