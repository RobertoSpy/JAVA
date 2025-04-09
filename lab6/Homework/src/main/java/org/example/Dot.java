package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Dot implements Serializable {
    private final int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Dot dot = (Dot) obj;
    return x == dot.x && y == dot.y;
}

@Override
public int hashCode() {
    return Objects.hash(x, y);
}
}