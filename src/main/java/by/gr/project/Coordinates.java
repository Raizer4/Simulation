package by.gr.project;

import java.util.Objects;

public class Coordinates {

    private final int x;
    private final int y;

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return y == that.y && x == that.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }

}
