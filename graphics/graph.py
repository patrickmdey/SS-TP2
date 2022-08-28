import matplotlib.pyplot as plt
import math


# polarization = Math.sqrt(Math.pow(speedSum[0], 2) + Math.pow(speedSum[1], 2)) / (particleAmount * speed);
#                polarizationFile.write(polarization + "\n");

#p.getId(), p.getPosition().getX(), p.getPosition().getY(), p.getDirection()));

speed = 0.03
polarizations = []
with open("../out.txt") as output_file:
    line = output_file.readline()
    while line:
        particleAmount = int(line)
        output_file.readline()

        speed_x = 0
        speed_y = 0
        for _ in range(particleAmount):
            line = output_file.readline()
            angle = float(line.split(' ')[3])
            speed_x += speed * math.cos(angle)
            speed_y += speed * math.sin(angle)

        polarizations.append(math.sqrt(speed_x ** 2 + speed_y ** 2) / (particleAmount * speed))
        line = output_file.readline()



plt.plot(polarizations)
plt.plot(polarizations)
plt.show()