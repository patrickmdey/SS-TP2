package main.java.ar.edu.itba.ss;

import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.models.Space;
import main.java.ar.edu.itba.ss.utils.ParticleGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DynamicNSimulation {
    final static int MAX_ITER = (int) Math.pow(10, 4);

    public static void main(String[] args) {
        int spaceSize = 7;
        int particleAmount = 300;
        double interactionRad = 1;
        double eta = 1;
        double speed = 0.03;

        Particle.setInteractRadius(interactionRad);
        List<Particle> particles;
        Space space;


        double[] etas = {0.3, 0.5, 0.7, 0.9, 1.1, 1.3, 1.5};

        try (FileWriter dynamicNFile = new FileWriter("dynamicN.txt")) {
            for (double e : etas) {
                double polarization = 0.5;
                particles = ParticleGenerator.generate("static.txt", particleAmount, spaceSize, e, speed);
                space = new Space(spaceSize, interactionRad, particles);

                int iterCount = 0;

                int samePolCounter = 0;
                double lastPol = 0;
                while (polarization < 0.9999999 && iterCount < MAX_ITER && samePolCounter < 7) {
                    particles = space.getParticleList();
                    double[] speedSum = {0, 0};

                    for (Particle p : particles) {
                        double sx = p.getSpeed() * Math.cos(p.getDirection());
                        double sy = p.getSpeed() * Math.sin(p.getDirection());
                        speedSum[0] += sx;
                        speedSum[1] += sy;
                    }
                    polarization = Math.sqrt(Math.pow(speedSum[0], 2) + Math.pow(speedSum[1], 2)) / (particleAmount * speed);

                    if (Math.abs(lastPol - polarization) < 0.00001) {
                        samePolCounter++;
                    } else {
                        samePolCounter = 0;
                    }

                    lastPol = polarization;

                    space.update();
                    iterCount++;
                }
                dynamicNFile.write(String.format(Locale.ROOT, "%f %f\n", e, polarization));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
