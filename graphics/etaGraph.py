import matplotlib.pyplot as plt


x_array = []
y_array = []
with open("../dynamicN.txt") as dynamic_n_file:
    for line in dynamic_n_file:
        
        [x, y] = [float(n) for n in line.split()]
        x_array.append(x)
        y_array.append(y)

dynamic_n_file.close()
fig, ax = plt.subplots()
ax.plot(x_array, y_array)
plt.show()