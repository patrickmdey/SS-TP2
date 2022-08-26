import matplotlib.pyplot as plt

polarizations = []
with open("../pol.txt") as polarization_file:
    line = polarization_file.readline()
    while line:
        polarizations.append(float(line))
        line = polarization_file.readline()
polarization_file.close()

# plt.errorbar(range(len(polarizations)), polarizations, yerr=0.001, color="black", antialiased=True)
plt.plot(polarizations)
plt.show()