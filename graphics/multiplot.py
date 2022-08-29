from cProfile import label
import matplotlib.pyplot as plt

import numpy as np

polarizations = []
MAX_ITER = 5000
with open('../multiplot.txt') as multiplot_file:
    line = multiplot_file.readline()
    idx = 0
    while line:
        polarizations.append([])
        for _ in range(MAX_ITER):
            line = multiplot_file.readline()
            polarizations[idx].append(float(line))

        line = multiplot_file.readline()
        idx += 1

multiplot_file.close()

plt.xlabel("Time (s)")
plt.ylabel("Polarization")

etas = np.arange(0, 2.1, 0.5)
for idx, polarization in enumerate(polarizations):
    plt.plot(polarization, label="eta = " + str(etas[idx]))

plt.axvline(x = 800, color = 'r', linestyle = '--')
plt.legend(loc='upper right')
plt.show() 

t = int(input("Enter a number: "))
for_average = [np.mean(polarizations[i][t:]) for i in range(len(polarizations))]
for_std = [np.std(polarizations[i][t:], ddof=1) for i in range(len(polarizations))]

plt.xlabel("Eta")
plt.ylabel("Polarization")

# TODO cambiar por eta y densidad (N / L^2)
plt.errorbar(etas, for_average, yerr=for_std, fmt='o', capsize=6, capthick=2)
plt.xticks(etas)
plt.yticks(np.arange(0, 1.1, 0.1))

plt.show()
