# PathFinding

## Description
A robot has to navigate through a series of small underground caverns connected by straight
tunnels. Some tunnels can only be navigated in one direction. The robot is given a map of the
caverns and tunnels which is given as the coordinates of the centre of each cavern, plus a binary
matrix showing which caverns can be reached from which other caverns.

## Input File format
Cavern maps are stored in .cav files which take the following format:
The file is a text file which contains a series of integers separated by commas.
The first integer gives the number of caverns - N.
The next N*2 integers give the coordinates of each of the caverns – each value is non-negative.
The final N*N integers give the connectivity of the tunnels. 1 means connected, 0 means not
connected. Remember that some tunnel are one-way.
The order of the connectivity matrix is a follows:
Connectivity of Cavern 1 to Cavern 1
Connectivity of Cavern 2 to Cavern 1
Connectivity of Cavern 3 to Cavern 1
Connectivity of Cavern 4 to Cavern 1
Connectivity of Cavern 5 to Cavern 1
.
.
.
Connectivity of Cavern 1 to Cavern 2
Connectivity of Cavern 2 to Cavern 2
Connectivity of Cavern 3 to Cavern 2
.
.
.
An example would be:
7,2,8,3,2,14,5,7,6,11,2,11,6,14,1,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,0,1,1,1,0,0,0,0,
0,1,1,0,0,0,0,0,1,0,0,0,
