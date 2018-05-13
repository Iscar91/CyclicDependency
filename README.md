# CyclicDependency
Check if an aritmethic expresion has a cyclic dependency, using graph theory.

Consider definitions of variables using expressions like this:

A = 2*B + 10 + 8*C – D/2

The left side of the expression contains a single uppercase letter, which is the name of the variable.

The right side of the expression contains arithmetic operations and/or uppercase letters representing
variables.

A cyclic dependency is a dependency by a variable on itself either directly in its definition or indirectly
via other variable definitions. Let’s consider these variable definitions:

A = 2*A

B = C + 5

C = 4*D

D = 8 + B

There are two cyclic dependencies in the example above. One of them is A -> A and other is
B -> C -> D -> B.
