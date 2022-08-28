import math

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
        
output_file.close()


with open("../multiplot.txt", "a") as multiplot_file:
    multiplot_file.write("titulo\n")
    for polarization in polarizations:
        multiplot_file.write(str(polarization) + "\n")
multiplot_file.close()
