# Graph Library and Cycle Detector

This project provides a basic Java-based graph library and an application that uses this library to detect cyclic dependencies in arithmetic expressions.

## Components

1.  **`Graph.java` (in `adjacencymatrix` package)**
    *   A simple graph library class that uses an adjacency matrix to represent a directed graph.
    *   Supports operations like adding/removing edges and retrieving the adjacency matrix.
    *   Currently designed for integer-indexed nodes.

2.  **`CycleDetector.java` (in `adjacencymatrix` package)**
    *   An application that checks if a set of arithmetic expressions (e.g., `A = 2*B`, `B = 1+C`) contains cyclic dependencies.
    *   It parses variable dependencies, builds a graph using `Graph.java`, and then uses matrix operations (specifically, checking the diagonal of the reachability matrix) to identify cycles.

## Cyclic Dependency Detection Example

Consider definitions of variables using expressions like this:

`A = 2*B + 10 + 8*C – D/2`

The left side of the expression contains a single uppercase letter, which is the name of the variable.
The right side of the expression contains arithmetic operations and/or uppercase letters representing variables.

A cyclic dependency is a dependency by a variable on itself either directly in its definition or indirectly via other variable definitions. Let’s consider these variable definitions:

`A = 2*A`
`B = C + 5`
`C = 4*D`
`D = 8 + B`

There are two cyclic dependencies in the example above. One of them is `A -> A` and other is `B -> C -> D -> B`. The `CycleDetector.java` application can identify such dependencies.

## Future Potential

The `Graph.java` class provides a foundation that could be extended with more standard graph algorithms (BFS, DFS, shortest path, etc.) and features (e.g., support for weighted edges, generic node types) to serve as a more general-purpose graph library.
