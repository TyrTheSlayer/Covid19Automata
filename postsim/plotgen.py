import pandas as pd
from matplotlib import pyplot as plt
import numpy as np

class plotgen:
	def __init__(self, filename):
		self.table = pd.read_csv(filename, header=None)

	def __init__(self):
		self.table = pd.read_csv("test.csv", header=None)

	def printdf(self):
		print(self.table.to_string())

	def generate_plot(self):
		days = self.table.iloc[0].values.tolist()[1:]
		infected = self.table.iloc[1].values.tolist()[1:]
		dead = self.table.iloc[2].values.tolist()[1:]
		alive = self.table.iloc[3].values.tolist()[1:]

		plt.plot(days, infected)
		plt.plot(days, dead)
		plt.plot(days, alive)

		plt.margins(x=0, y=0)
		plt.fill_between(np.arange(0, len(days)), infected, dead)
		plt.xticks(days)
		plt.title("Infection Population Line Plot")
		plt.xlabel("Number of Days")
		plt.legend(["Infected", "Dead", "Alive"])
		plt.show()


	