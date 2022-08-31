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

eta_count = 6

t = 320

N=[40, 100, 400, 4000]
L=[3.1262, 5, 10, 31.6228]
for i in range(len(N)):
    pol = polarizations[eta_count * i: eta_count * (i + 1)]
    for_average = [np.mean(pol[i][t:]) for i in range(len(pol))]
    for_std = [np.std(pol[i][t:], ddof=1) for i in range(len(pol))]

    plt.errorbar(list(range(eta_count)), for_average, yerr=for_std, fmt='o-', capsize=6, capthick=2, label=f'N = {N[i]}, L = {L[i]}')


plt.legend(loc='upper right')

plt.xlabel("Eta")
plt.ylabel("Polarization")

plt.show()
