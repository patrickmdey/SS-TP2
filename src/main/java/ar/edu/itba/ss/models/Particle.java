package main.java.ar.edu.itba.ss.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Particle {
    private static double INTERACT_RADIUS;
    private static long SEQ = 0;
    private Point position;
    private final long id;
    private final Set<Particle> neighbours;
    private final double speed;
    private double direction;
    private final double eta;

    public Particle(double speed, double eta) {
        this.id = SEQ++;
        this.neighbours = new HashSet<>();
        this.speed = speed;
        this.direction = Math.random() * 2 * Math.PI;
        this.eta = eta;
    }

    public void update(int spaceSize) {
        this.interact();
        this.updatePosition(spaceSize);
    }

    private void updatePosition(int spaceSize) {
        double dx = this.speed * Math.cos(this.direction);
        double dy = this.speed * Math.sin(this.direction);
        this.position.move(spaceSize, dx, dy);
    }

    private void interact() {
        double totSin = Math.sin(this.direction);
        double totCos = Math.cos(this.direction);
        for (Particle neighbour : this.neighbours) {
            totSin += Math.sin(neighbour.direction);
            totCos += Math.cos(neighbour.direction);
        }

        double count = this.neighbours.size() + 1;
        this.direction = (Math.atan2(totSin / count, totCos / count) + Math.random() * this.eta - this.eta / 2);
    }

    public boolean isColliding(Particle other, int spaceSize, int gridM) {
        if (this.equals(other))
            return false;

        double realDistance = position.distanceTo(other.getPosition(), spaceSize, gridM);

        return Double.compare(realDistance, INTERACT_RADIUS) <= 0;
    }

    public void addNeighbour(Particle neighbour) {
        neighbours.add(neighbour);
    }

    public void removeAllNeighbours() {
        neighbours.clear();
    }

    public static double getInteractRadius() {
        return INTERACT_RADIUS;
    }

    public static void setInteractRadius(double interactRadius) {
        INTERACT_RADIUS = interactRadius;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public Set<Particle> getNeighbours() {
        return neighbours;
    }

    @Override
    public String toString() {
        return "Particle{" + "id=" + id + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return getId() == particle.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
