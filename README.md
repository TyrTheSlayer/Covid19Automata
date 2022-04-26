# Covid-19 Automata Simulator

## Introduction

In the last few years, COVID19 has affected the lives of every person in a large way. Our goal with this project is to provide visualization to further understand the consequences of the deadly pandemic. We want to do this through a cellular automata model and plan on extending the model to be more realistic and representative of the real world, if time permits.

## Group Members

  Aedan Wells

  Summer Bronson
  
  Wesley Camphouse
  
  Brenton Candelaria
  
  Jonathan Carsten
  
  Samuel Nix
  
  
## Contents

* diagrams: Contains all UML and assorted diagrams for the project. Broken down into different versions as code changes/improvements were made
* project: Contains the general project code and subsequent modules
    *UI: Holds the UI implementation for initial menu, running/interacting with the simulation, and post simulation graph interaction
        *UI.java: intial menu
        *MainFrame.java: During simulation UI, can change speed of running and exit to post simulation
        *PostSimUI.java: User interface after simulation
    *Simulator: 
        *BehaviorAgent.java: Handles intelligent behaviors of the automata, such as intent and direction for user movement
        *DataOut.java: handles the data output to a csv for graph creation for the post simulation
        *Factor.java: handles the factors influencing infection like age, vaccination, mask
        *Intent.java: handles status/actions for a cell and stores data to be used by the BehaviorAgent
        *SimSettings.java: Holds and sets the settings changed by the user at the initial menu
    *Path: only holds Path.java, implements the logic for intelligent pathing for people
    *Grid: Holds logic for all objects on the grid
        *Building.java: Class that represents a building, implements logic for the different buildings
        *BuildingType.java: Basic enum to track the building types
        *GridPanel.java: creates the grid the mainframe will display to the user
        *Tile.java: implements the logic for the tiles in the simulation
    *DataObjects:
        *DailySchedule.java: class defining logic for users on where they go and how, increases intelligent movement
        *Person.java: person object that keeps track of all of the data for the people. 
        *Status.java: the status enum for people, determining infeciton and death status
        *TimeCard.java: data storage for a scheduled obligation a person might have
        *Virus.java: object used to represent the instance of a virus
        *VirusStage.java: enum for the stage of a virus, incubating, contagious, fatal etc.
        *VirusType.java: logic for the virus type, giving details of contagious time and such. 
* reports: Contains the reports required for the CSE326 Software Engineering course
