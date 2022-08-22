package main.java.ar.edu.itba.ss.models;

public class Point {
    protected double x;
    protected double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other, int spaceSize, int gridM) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
    }

    public void move(int spaceSize, double dx, double dy) {
        this.x = (spaceSize + this.x + dx) % spaceSize;
        this.y = (spaceSize + this.y + dy) % spaceSize;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
