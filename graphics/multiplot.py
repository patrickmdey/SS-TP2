import matplotlib.pyplot as plt

polarizations = []
MAX_ITER = 10**4
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

for polarization in polarizations:
    plt.plot(polarization)

plt.show()

# def plot(arr: dict, title: str):
    
#     x = np.array(range(len(arr["max"])))

#     fig, ax = plt.subplots(figsize=(5, 2.7), layout="constrained")

#     ax.set_xlabel("gen n")
#     ax.set_ylabel("fitness")
#     ax.locator_params("y", nbins=10)
#     ax.locator_params("x", nbins=15)
#     ax.plot(x, arr["max"], label="max", color="r")
#     ax.plot(x, arr["min"], label="min", color="g")
#     ax.plot(x, arr["avg"], label="avg", color="b")
#     ax.set_title(title)
#     ax.legend()
#     plt.show()
