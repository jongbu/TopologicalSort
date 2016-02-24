# TopologicalSort
It is a Java program which performs a topological sorting on the graph using linked lists as the data structure.

This was one of my projects at Queens College, CUNY for CS323 "Design and Analysis of Algorithms". The input and output files can be found in the projects directory as well. 

Here is the Guidelines of this projects provided by the professor. 

The code must work from the command line using the Java jdk: being compiled by javac and run by the java command. There should be two parameters to your program: the first is an input file containing the adjacency matrix of a directed graph; the second is an output file that you will print out to. The project is to determine whether or not a cycle exists in the graph BY

1) Performing a topological sort on the graph

2) ONLY using matrices/arrays and/or linked lists as your data structures. No graph APIs or hash table/map APIs may  be used. But, you then can create your OWN derived classes.

3) The code must be yours.

The input file format must follow the following:
first line is the number of vertices n
the next n lines must contain the adjacency matrix row by row, each row containing a binary string of length n

The output file format can be decided by you, but must FIRST output the following:
If the inputted graph has a cycle, then your output will state so and report the first vertex that is known to be part of the cycle.
If the inputted graph does not have a cycle, then your output will state so and provide the listing of all the vertices according to the topological sort.

