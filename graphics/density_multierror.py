import matplotlib.pyplot as plt

import numpy as np

polarizations = []
MAX_ITER = 1000
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

t = 400

N=[250, 500, 750, 1000, 1250]
L = 25

densities = [n/(L**2) for n in N]

for_average = [np.mean(polarizations[i][t:]) for i in range(len(polarizations))]
for_std = [np.std(polarizations[i][t:], ddof=1) for i in range(len(polarizations))]

plt.errorbar(densities, for_average, yerr=for_std, fmt='o-', capsize=6, capthick=2)

plt.yticks(np.arange(0, 1.1, 0.1))
plt.xlabel("Density")
plt.ylabel("Polarization")

plt.show()
