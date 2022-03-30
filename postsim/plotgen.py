import pandas as pd
from matplotlib import pyplot as plt
import numpy as np

class plotgen:
	def __init__(self, filename):
		self.table = pd.read_csv(filename, header=None)
		self.days = self.table.iloc[0].values.tolist()[1:]
		self.infected = self.table.iloc[1].values.tolist()[1:]
		self.dead = self.table.iloc[2].values.tolist()[1:]
		self.alive = self.table.iloc[3].values.tolist()[1:]

	def __init__(self):
		self.table = pd.read_csv("test.csv", header=None)
		self.days = self.table.iloc[0].values.tolist()[1:]
		self.infected = self.table.iloc[1].values.tolist()[1:]
		self.dead = self.table.iloc[2].values.tolist()[1:]
		self.alive = self.table.iloc[3].values.tolist()[1:]

	def printdf(self):
		print(self.table.to_string())

	def plot_density(self):
		plt.plot(self.days, self.infected)
		plt.plot(self.days, self.dead)
		plt.plot(self.days, self.alive)

		plt.margins(x=0, y=0)

		plt.fill_between(np.arange(1, len(self.days)+1), self.infected, self.dead)
		plt.fill_between(np.arange(1, len(self.days)+1), self.dead, 0)
		plt.fill_between(np.arange(1, len(self.days)+1), self.alive, self.infected)

		plt.xticks(self.days)
		plt.title("Infection Population Line Plot")
		plt.xlabel("Number of Days")
		plt.legend(["Infected", "Dead", "Alive"])
		plt.show()

	def plot_line(self, line="infected"):
		if(line == "infected"):
			plt.plot(self.days, self.infected)
		elif(line == "alive"):
			plt.plot(self.days, self.alive)
		elif(line == "dead"):
			plt.plot(self.days, self.dead)	
		else:
			raise NameError("parameter must be : alive, dead, infected")

		plt.margins(y=0)
		plt.xticks(self.days)
		plt.title(line + " Plot")
		plt.xlabel("Number of Days")
		plt.show()