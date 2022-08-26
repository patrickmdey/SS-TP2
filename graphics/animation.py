import math


num_lines = sum(1 for line in open('../out.txt'))

with open('../out.txt', "r") as out_file:
    with open('../ovito.txt', "w") as ovito_file:
        for idx in out_file:
            parts = idx.split()
            if len(parts) > 1:
                vx = float(parts[2])
                vy = float(parts[3])
                ovito_file.write(f"{parts[0]} {parts[1]} {angle}\n")
            else:
                ovito_file.write(idx)
    ovito_file.close()
out_file.close()
