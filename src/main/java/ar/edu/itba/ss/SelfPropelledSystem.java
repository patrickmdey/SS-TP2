package main.java.ar.edu.itba.ss;

import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.models.Space;
import main.java.ar.edu.itba.ss.utils.ParticleGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SelfPropelledSystem {
    final static int MAX_ITER = (int) Math.pow(10, 4);

    public static void main(String[] args) {
        int spaceSize = 25;
        int particleAmount = 300;
        double interactionRad = 1;
        double eta = 0.1;
        double speed = 0.03;

        Particle.setInteractRadius(interactionRad);

        List<Particle> particles = ParticleGenerator.generate("static.txt",
                particleAmount, spaceSize, eta, speed);

        Space space = new Space(spaceSize, interactionRad, particles);

        double polarization = 0.5;

        int iterCount = 0;
        try (FileWriter writer1 = new FileWriter("out.txt");
             FileWriter polarizationFile = new FileWriter("pol.txt")) {
            while (polarization < 0.9999999 && iterCount < MAX_ITER) {
                particles = space.getParticleList();
                double[] speedSum = {0,0};
                writer1.write(particleAmount + "\n");
                writer1.write("title" + "\n");

                for (Particle p : particles) {
                    double sx = p.getSpeed() * Math.cos(p.getDirection());
                    double sy = p.getSpeed() * Math.sin(p.getDirection());
                    writer1.write(String.format(Locale.ROOT, "%f %f %f %f\n",
                            p.getPosition().getX(), p.getPosition().getY(), sx, sy));

                    speedSum[0] += sx;
                    speedSum[1] += sy;
                }

                polarization = Math.sqrt(Math.pow(speedSum[0], 2) + Math.pow(speedSum[1], 2)) / (particleAmount * speed);
                polarizationFile.write(polarization + "\n");

                space.update();
                iterCount++;
            }
            System.out.println("Iterations: " + iterCount);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
