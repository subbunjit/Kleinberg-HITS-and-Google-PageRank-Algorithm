# Kleinberg-HITS-and-Google-PageRank-Algorithm
This program is a Java Implementation for HITS and PageRank Algorithm.

# Usage
java hits3232 iterations initialvalue filename
java pgrk3232 iterations initialvalue filename

# Iterations
For iteration = 0, the algorithms run until the iteration values converge with an error rate of 0.00001. That is, the iterations are computed until the difference between the current and last iteration values is less than 0.00001. Only the values for the last iteration are displayed.

For non zero iteration values, all the iterations are computed and displayed.

For graphs with nodes greater than 10, only values for the last iteration are displayed (iterations is assumed to be 0, and initialvalue = -1).
