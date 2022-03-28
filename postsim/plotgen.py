import pandas as pd
import matplotlib as plt

class plotgen:
	def __init__(self, filename):
		self.table = pd.read_csv(filename)

	def __init__(self):
		self.table = pd.read_csv("test.csv")

	def printdf(self):
		print(self.table.head())
	
