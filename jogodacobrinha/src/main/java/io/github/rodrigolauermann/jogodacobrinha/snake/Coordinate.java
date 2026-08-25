package io.github.rodrigolauermann.jogodacobrinha.snake;

public class Coordinate {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordinate)) return false;

        Coordinate outra = (Coordinate) obj;
        return this.x == outra.x && this.y == outra.y;
    }
}

