package main.java.ar.edu.itba.ss.models;

import java.util.List;

public class Space {
    private final static int[][] DIRECTIONS = new int[][]{new int[]{-1, 0}, new int[]{-1, 1},
            new int[]{0, 0}, new int[]{0, 1}, new int[]{1, 1}};

    private final Cell[][] cells;
    private final int spaceSize;
    private final int gridM;
    private final double cellSize;
    private final List<Particle> particleList;

    public Space(int spaceSize, double interactionRadius, List<Particle> particles) {
        this.spaceSize = spaceSize;
        this.gridM = (int) Math.floor(spaceSize / (interactionRadius));
        this.cellSize = (double) spaceSize / gridM;

        this.cells = new Cell[gridM][gridM];

        this.particleList = particles;
//        this.positionParticles();
    }

    public void update() {
        this.positionParticles();
        this.calculateNeighbours();

        particleList.forEach(Particle::calculateDirection);
        particleList.forEach(p -> p.update(spaceSize));
    }

    private void positionParticles() {
        // TODO: Ver si se puede hacer mejor
        for (int i = 0; i < gridM; i++) {
            for (int j = 0; j < gridM; j++) {
                this.cells[i][j] = null;
            }
        }

        for (Particle particle : this.particleList) {
            Point position = particle.getPosition();
            int row = getRow(position);
            int col = getCol(position);
            if (cells[row][col] == null)
                cells[row][col] = new Cell();

            cells[row][col].addParticle(particle);
        }
    }
    public void calculateNeighbours() {
        this.particleList.forEach(particle -> {
            particle.removeAllNeighbours();

            Point position = particle.getPosition();
            int row = getRow(position);
            int col = getCol(position);

            for (int[] dir : DIRECTIONS) {
                int currRow = Math.floorMod(row + dir[0], gridM);
                int currCol = Math.floorMod(col + dir[1], gridM);

                if (cells[currRow][currCol] == null)
                    continue;

                cells[currRow][currCol].getParticles().stream()
                        .filter(p -> particle.isColliding(p, spaceSize, gridM))
                        .forEach(p -> {
                            particle.addNeighbour(p);
                            p.addNeighbour(particle);
                        });
            }
        });
    }

//    private void periodicSet() {
//        this.particleList.forEach(particle -> {
//            particle.removeAllNeighbours();
//
//            Point position = particle.getPosition();
//            int row = getRow(position);
//            int col = getCol(position);
//
//            for (int[] dir : DIRECTIONS) {
//                int currRow = Math.floorMod(row + dir[0], gridM);
//                int currCol = Math.floorMod(col + dir[1], gridM);
//
//                if (cells[currRow][currCol] == null)
//                    continue;
//
//                cells[currRow][currCol].getParticles().stream()
//                        .filter(p -> particle.isColliding(p, spaceSize, gridM))
//                        .forEach(p -> {
//                            particle.addNeighbour(p);
//                            p.addNeighbour(particle);
//                        });
//            }
//        });
//    }

    private void setNeighbours() {
        this.particleList.forEach(particle -> {
            particle.removeAllNeighbours();
            Point position = particle.getPosition();
            int row = getRow(position);
            int col = getCol(position);

            for (int[] dir : DIRECTIONS) {
                int currRow = row + dir[0];
                int currCol = col + dir[1];

                if (currRow < 0 || currRow >= gridM || currCol < 0
                        || currCol >= gridM || cells[currRow][currCol] == null)
                    continue;

                cells[currRow][currCol].getParticles().stream()
                        .filter(p -> particle.isColliding(p, spaceSize, gridM))
                        .forEach(p -> {
                            particle.addNeighbour(p);
                            p.addNeighbour(particle);
                        });
            }
        });

        System.out.println("Despues hay: " + (Integer) particleList.stream().map(p -> p.getNeighbours().size()).mapToInt(s -> s).sum());
    }

    private int getRow(Point position) {
        return (int) (position.getX() / cellSize);
    }

    private int getCol(Point position) {
        return (int) (position.getY() / cellSize);
    }

    public List<Particle> getParticleList() {
        return particleList;
    }
}
