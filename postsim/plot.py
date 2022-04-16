from plotgen import plotgen
import argparse

def main():
	parser = argparse.ArgumentParser()
	parser.add_argument("-d", "--density", action="store_true")
	parser.add_argument("-l", "--line", help="argument should be \"infected\", \"dead\", \"recovered\", or \"susceptible\"")
	args = parser.parse_args()

	plotter = plotgen()

	# plot a density chart
	if(args.density == True):
		plotter.plot_density()
	elif(args.line != None):
		print type(args.l)
		plotter.plot_line(args.l)


if __name__ == "__main__":
	main()

