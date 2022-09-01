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


plt.xlabel("Time (s)")
plt.ylabel("Polarization")

N = [250, 500, 750, 1000, 1250]
for idx, polarization in enumerate(polarizations):
    plt.plot(polarization, label=f'density = {N[idx]/25**2}')

plt.axvline(x = 400, color = 'r', linestyle = '--')
plt.legend(loc='lower right')
plt.show()