package org.example;

import java.io.Serializable;

public class Line implements Serializable {
    private final Dot start;
    private final Dot end;

    public Line(Dot start, Dot end) {
        this.start = start;
        this.end = end;
    }

    public Dot getStart() {
        return start;
    }

    public Dot getEnd() {
        return end;
    }

    public int getLength() {
        int dx = start.getX() - end.getX();
        int dy = start.getY() - end.getY();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
}