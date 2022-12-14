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
    private double nextDir;
    private final double eta;

    public Particle(double speed, double eta) {
        this.id = SEQ++;
        this.neighbours = new HashSet<>();
        this.speed = speed;
        this.direction = Math.random() * 2 * Math.PI;
        this.eta = eta;
    }

    public void calculateDirection() {
        double totSin = Math.sin(this.direction);
        double totCos = Math.cos(this.direction);
        for (Particle neighbour : this.neighbours) {
            totSin += Math.sin(neighbour.direction);
            totCos += Math.cos(neighbour.direction);
        }

        double count = this.neighbours.size() + 1;
        double newDir = (Math.atan2(totSin / count, totCos / count) + Math.random() * this.eta - this.eta / 2);

        this.nextDir = (newDir + 2 * Math.PI) % (2 * Math.PI);
    }

    private void updatePosition(double spaceSize) {
        double dx = this.speed * Math.cos(this.direction);
        double dy = this.speed * Math.sin(this.direction);
        this.position.move(spaceSize, dx, dy);
    }

    public void update(double spaceSize) {
        this.direction = this.nextDir;
        this.updatePosition(spaceSize);
    }

    public boolean isColliding(Particle other, double spaceSize, int gridM) {
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
