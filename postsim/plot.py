"""
@author
"""
from plotgen import plotgen
import argparse
from datetime import datetime

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("-d", "--density", action="store_true")
    parser.add_argument("-l", "--line",
                        help="argument should be \"infected\", \"dead\", \"recovered\", or \"susceptible\"")
    parser.add_argument("-p", "--people", action="store_true")
    args = parser.parse_args()

    plotter = plotgen()

    # plot a density chart
    if (args.density == True):
        plotter.plot_density()
    # plot line graphs
    if (args.line != None):
        plotter.plot_line(args.line)
    # plot poeple graph
    if (args.people != None):
        plotter.plot_people()

# entry point for generator
if __name__ == "__main__":
    start_time = datetime.now()
    main()
    end_time = datetime.now()
    print("Took %s seconds" % (end_time - start_time))
