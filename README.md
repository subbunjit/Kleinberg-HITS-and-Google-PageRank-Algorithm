# Kleinberg-HITS-and-Google-PageRank-Algorithm
This program is a Java Implementation for HITS and PageRank Algorithm.

# Usage
java hits3232 iterations initialvalue filename
java pgrk3232 iterations initialvalue filename

# Iterations
For iteration = 0, the algorithms run until the iteration values converge with an error rate of 0.00001. That is, the iterations are computed until the difference between the current and last iteration values is less than 0.00001. Only the values for the last iteration are displayed.

For non zero iteration values, all the iterations are computed and displayed.

For graphs with nodes greater than 10, only values for the last iteration are displayed (iterations is assumed to be 0, and initialvalue = -1).

# Initial Value
This command line argument can take values in the range from -2, -1, 0, 1.

For -2, the hub/authority or pagerank values are initialized as 1/(sqrt(n)), for all the nodes, where n is the number of nodes.

For -1, the hub/authority or pagerank values are initialized as 1/(n), for all the nodes, where n is the number of nodes.

For 0, the hub/authority or pagerank values are initialized as 0 for all the nodes.

For 1, the hub/authority or pagerank values are initialized as 1 for all the nodes.

# Filename
This text file contains the representation of a graph.

The first number of the first line represents the number of nodes (n) of the graph.

The second number of the first line represents the number of edges (m) of the graph.

Remaining line (i j) represents the edge from node i to node j in the graph.

# Hits
hits3232.java computes the authority and hub values of nodes (web pages) in a network represented here as a directed graph.

# PageRank
pgrk3232.java computes the pagerank values of nodes (web pages) in a network represented here as a directed graph.
