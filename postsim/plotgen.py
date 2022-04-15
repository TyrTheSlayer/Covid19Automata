import pandas as pd
from matplotlib import pyplot as plt
from matplotlib import patches as mpatches
import numpy as np
import seaborn as sns
from scipy.interpolate import make_interp_spline, BSpline

class plotgen:
	def __init__(self, filename):
		self.table = pd.read_csv(filename, header=None, skiprows=1)
		self.days = len(self.table.index)
		self.suscep = self.table.iloc[0].values.tolist()[1:]
		self.infected = self.table.iloc[1].values.tolist()[1:]
		self.receovered = self.table.iloc[2].values.tolist()[1:]
		self.dead = self.table.iloc[3].values.tolist()[1:]
		self.vaccinated = self.table.iloc[4].values.tolist()[1:]

	def __init__(self):
		self.table = pd.read_csv("simulation.csv")
		self.days = len(self.table)
		self.suscep = self.table["susceptible"].tolist()
		self.infected = self.table["infected"].tolist()
		self.recovered = self.table["recovered"].tolist()
		self.dead = self.table["dead"].tolist()
		self.vaccinated = self.table["vaccinated"].tolist()

	def printdf(self):
		print(self.table.to_string())

	def plot_density(self):
		sns.set_style("darkgrid", {"axes.facecolor" : ".85"})
		sns.set_context("paper")

		x_axis = [elem for elem in range(0, self.days)]
		x_axis = np.array(x_axis)

		pop_list = [600 for d in range(0, self.days)]

		infected_bar = [i + j + k for i,j,k in zip(self.infected, self.recovered, self.dead)]
		recovered_bar = [i + j for i,j in zip(self.recovered, self.dead)]

		sns.barplot(x=x_axis, y=infected_bar, color="darkblue")
		sns.barplot(x=x_axis, y=recovered_bar, color="lightblue")
		sns.barplot(x=x_axis, y=self.dead, color="red")

		plt.title("Infection Population Line Plot", fontsize=13)
		plt.xlabel("Number of Weeks", fontsize=11)
		plt.ylabel("Total Population", fontsize=11)

		bar1 = mpatches.Patch(color='darkblue', label='Infected')
 		bar2 = mpatches.Patch(color='lightblue', label='Recovered')
 		bar3 = mpatches.Patch(color='red', label='Dead')
		plt.legend(handles=[bar1, bar2, bar3], bbox_to_anchor=(1,1))
		plt.tight_layout()
		plt.savefig("density.png")
		#plt.show()


	def plot_line(self, line="infected"):
		print "plotting line"
		sns.set_style("darkgrid", {"axes.facecolor" : ".85"})
		sns.set_context("paper")

		x_axis = [elem for elem in range(0, self.days)]
		x_axis = np.array(x_axis)

		if(line == "infected"):
			x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
			spl = make_interp_spline(x_axis, np.array(self.infected), k=3)
			y_new = spl(x_new)
			plot = sns.lineplot(x=x_new, y=y_new, data=self.table, linewidth=3)
		elif(line == "susceptible"):
			x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
			spl = make_interp_spline(x_axis, np.array(self.suscep), k=3)
			y_new = spl(x_new)
			plot = sns.lineplot(x=x_new, y=y_new, data=self.table, linewidth=3)
		elif(line == "dead"):
			x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
			spl = make_interp_spline(x_axis, np.array(self.dead), k=3)
			y_new = spl(x_new)
			plot = sns.lineplot(x=x_new, y=y_new, data=self.table, linewidth=3)
		elif(line == "recovered"):
			x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
			spl = make_interp_spline(x_axis, np.array(self.recovered), k=3)
			y_new = spl(x_new)
			plot = sns.lineplot(x=x_new, y=y_new, data=self.table, linewidth=3)
		else:
			raise NameError("parameter must be : susceptible, dead, recovered, or infected")

		plt.title(line.capitalize() + " Plot", fontsize=13)
		plot.set_xlabel("Weeks", fontsize=11)
		plot.set_ylabel("Number of " + line.capitalize(), fontsize=11)
		plt.savefig("line.png")
		#plt.show()