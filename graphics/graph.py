import matplotlib.pyplot as plt

polarizations = []
with open("../pol.txt") as polarization_file:
    line = polarization_file.readline()
    while line:
        polarizations.append(float(line))
        line = polarization_file.readline()

plt.plot(polarizations)
plt.show()
