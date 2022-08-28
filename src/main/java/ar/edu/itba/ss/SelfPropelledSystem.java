package main.java.ar.edu.itba.ss;

import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.models.Space;
import main.java.ar.edu.itba.ss.utils.ParticleGenerator;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SelfPropelledSystem {
    final static int MAX_ITER = (int) Math.pow(10, 4.5);

    public static void main(String[] args) {
        int spaceSize = 25;
        int particleAmount = 300;
        double interactionRad = 1;
        double eta = 0.1;
        double speed = 0.03;

        Particle.setInteractRadius(interactionRad);

        List<Particle> particles = ParticleGenerator.generate("static.txt", particleAmount,
                spaceSize, eta, speed);

        Space space = new Space(spaceSize, interactionRad, particles);

        try (FileWriter outFile = new FileWriter("out.txt")) {
            for (int i = 0; i < MAX_ITER; i++) {
                particles = space.getParticleList();
                outFile.write(particleAmount + "\n");
                outFile.write("iter " + i + "\n");

                for (Particle p : particles)
                    outFile.write(String.format(Locale.ROOT, "%d %f %f %f\n", p.getId(),
                            p.getPosition().getX(), p.getPosition().getY(), p.getDirection()));

                space.update();
            }
//            System.out.println("Iterations: " + iterCount);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
