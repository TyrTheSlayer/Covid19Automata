"""
@author Jonathan Carsten

Plot Generator class
Includes methods for line, population density, and population demographic plots
"""
import pandas as pd
from matplotlib import pyplot as plt
from matplotlib import patches as mpatches
import numpy as np
import seaborn as sns
from scipy.interpolate import make_interp_spline, BSpline
from csv import reader


class plotgen:
    def __init__(self):
        self.table = pd.read_csv("simulation.csv")
        self.days = len(self.table)
        self.suscep = self.table.loc[:, "susceptible"].tolist()
        self.infected = self.table.loc[:, "infected"].tolist()
        self.recovered = self.table.loc[:, "recovered"].tolist()
        self.dead = self.table.loc[:, "dead"].tolist()
        self.vaccinated = self.table.loc[:, "vaccinated"].tolist()

        with open("people.csv", "r") as file:
            self.dflist = list()
            rowlist = list()
            filereader = reader(file)
            next(filereader)
            for line in file:
                line2 = line.split(",")
                if line2[0][:3] == "---":
                    newdf = pd.DataFrame(rowlist)
                    self.dflist.append(newdf)
                    rowlist = list()
                elif line2[0] == "age":
                    continue
                else:
                    rowlist.append(
                        {"age": line2[0], "status": line2[1], "vaccinated": line2[2]})

            self.dflist.append(pd.DataFrame(rowlist))

    def printdf(self):
        print(self.table.to_string())

    def plot_people(self):
        sns.set_style("darkgrid", {"axes.facecolor": ".85"})
        sns.set_context("paper")
        x_axis = ["0 to 10",
                  "11 to 20",
                  "21 to 30",
                  "31 to 40",
                  "41 to 50",
                  "51 to 60",
                  "61 to 70",
                  "71 to 80",
                  "81 to 90"]

        alive_bar = list()
        infected_bar = list()
        dead_bar = list()
        recovered_bar = list()

        for frame in self.dflist:
            alive_bar.append(frame[frame.status == "Alive"].shape[0])
            infected_bar.append(frame[frame.status == "Infected"].shape[0])
            dead_bar.append(frame[frame.status == "Dead"].shape[0])
            recovered_bar.append(frame[frame.status == "Recovered"].shape[0])

        plotdf = pd.DataFrame({'Range': x_axis,
                               'Alive': alive_bar,
                               'Infected': infected_bar,
                               'Recovered': recovered_bar,
                               'Dead': dead_bar})

        plt.tight_layout()
        plotdf.set_index('Range').plot(kind='bar', stacked=True, color=[
            'green', 'darkblue', 'lightblue', 'red'], figsize=(6.4, 5.2), rot=360,
            ylabel="Age Ranges", xlabel="Population", title="Age Range Population Information",
            fontsize=8)
        plt.savefig("people.png")

    def plot_density(self):
        sns.set_style("darkgrid", {"axes.facecolor": ".85"})
        sns.set_context("paper")

        x_axis = [elem for elem in range(0, self.days)]
        x_axis = np.array(x_axis)

        infected_bar = [i + j + k for i, j, k in zip(self.infected, self.recovered, self.dead)]
        recovered_bar = [i + j for i, j in zip(self.recovered, self.dead)]

        sns.barplot(x=x_axis, y=infected_bar, color="darkblue")
        sns.barplot(x=x_axis, y=recovered_bar, color="lightblue")
        sns.barplot(x=x_axis, y=self.dead, color="red")

        plt.title("Infection Population Density Plot", fontsize=13)
        plt.xlabel("Number of Weeks", fontsize=11)
        plt.ylabel("Population", fontsize=11)

        bar1 = mpatches.Patch(color='darkblue', label='Infected')
        bar2 = mpatches.Patch(color='lightblue', label='Recovered')
        bar3 = mpatches.Patch(color='red', label='Dead')
        plt.legend(handles=[bar1, bar2, bar3], bbox_to_anchor=(1, 1))
        plt.tight_layout()
        # plt.ylim(0, 600)
        plt.savefig("density.png")
        # plt.show()

    def plot_line(self, line="infected"):
        sns.set_style("darkgrid", {"axes.facecolor": ".85"})
        sns.set_context("paper")

        x_axis = [elem for elem in range(0, self.days)]
        x_axis = np.array(x_axis)

        if(line == "infected"):
            x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
            spl = make_interp_spline(x_axis, np.array(self.infected), k=3)
            y_new = spl(x_new)
            plot = sns.lineplot(x=x_new, y=y_new, linewidth=3)
        elif(line == "susceptible"):
            x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
            spl = make_interp_spline(x_axis, np.array(self.suscep), k=3)
            y_new = spl(x_new)
            plot = sns.lineplot(x=x_new, y=y_new, linewidth=3)
        elif(line == "dead"):
            x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
            spl = make_interp_spline(x_axis, np.array(self.dead), k=3)
            y_new = spl(x_new)
            plot = sns.lineplot(x=x_new, y=y_new, linewidth=3)
        elif(line == "recovered"):
            x_new = np.linspace(x_axis.min(), x_axis.max(), 150)
            spl = make_interp_spline(x_axis, np.array(self.recovered), k=3)
            y_new = spl(x_new)
            plot = sns.lineplot(x=x_new, y=y_new, linewidth=3)
        else:
            raise NameError("parameter must be : susceptible, dead, recovered, or infected")

        plt.title(line.capitalize() + " Plot", fontsize=13)
        plot.set_xlabel("Weeks", fontsize=11)
        plot.set_ylabel("Number of " + line.capitalize(), fontsize=11)
        plt.savefig("line.png")
        # plt.show()
