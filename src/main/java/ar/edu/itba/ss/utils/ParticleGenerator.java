package main.java.ar.edu.itba.ss.utils;

import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.models.PeriodicPoint;
import main.java.ar.edu.itba.ss.models.Point;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParticleGenerator {

    public static List<Particle> generate(String staticFile,
                                int totalParticles, int spaceSize, double eta, double speed) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < totalParticles; i++) {
            Particle p = new Particle(speed, eta);
            p.setPosition(randomPosition(spaceSize));
            particles.add(p);
        }

        try (FileWriter writer = new FileWriter(staticFile)) {
            writer.write(totalParticles + "\n");
            writer.write(spaceSize + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return particles;
    }

    private static Point randomPosition(double spaceSize) {
        double x = Math.random() * spaceSize;
        double y = Math.random() * spaceSize;
        return new PeriodicPoint(x, y);
    }
}
