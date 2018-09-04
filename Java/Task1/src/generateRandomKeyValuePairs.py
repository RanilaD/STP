import random
if __name__ == "__main__":
    f = open("keyValuePairs.txt", "w")
    for i in range(2500):
        f.write(f"{random.randint(1, 9999)}:{random.randint(1, 9999)}\n")