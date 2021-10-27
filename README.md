# CS3340_Analysis_of_Algorithms
## Assignment1:
### F(n) = F(n − 1) + F(n − 2), n > 1; F(1) = 1; F(0) = 0
### asn1_a.cpp:
Write a recursive function to compute F(n) using the above definition directly. Implement your solution and print F(i ∗ 5), where 0 ≤ i ≤ 10, as output.
### asn1_b.cpp:
Write a recursive function/procedure to compute F(n) with time complexity O(n) (more precisely, the time complexity should be O(nA(n)) when n is large, where A(n) is the complexity of adding F(n−1) and F(n−2)). Implement your solution and print F(i∗20), where 0 ≤ i ≤ 25, as output. This program must be able to compute F(n) precisely for n ≤ 500.
### asn1_c.cpp:
Write a recursive function/procedure to compute F(n) with time complexity O(log(n)) (more precisely, the time complexity should be O(log(n)A(n)) when n is large, where A(n) is the complexity of adding or multiplying F(i) and F(j)).

## Assignment2:
### Disjointset.java
A Disjoint-Set data structure should be implemented, with the most efficient algorithm (union by rank and path compression), as an abstract data type (a class in C++ or java) with the following operations.
##### **uandf(n)**: constructs an disjoint-set data type with n elements, 1, 2, . . . , n.
##### **make_set(i)**: creates a new set whose only member (and thus representative) is i.
##### **union_sets(i, j)**: unites the dynamic sets that contains i and j, respectively, into a new set that is the union of these two sets.
##### **find_set(i)**: returns the representative of the set containing i.
##### **final_sets()**: returns the total number of current sets and finalizes the current sets:(i) make set() and union sets() will have no effect after this operation and (ii) resets the representatives
### question10.java
Design and implement (write a program) an algorithm to find the connected components in a binary image using Disjoint-Set data structure in a).An ASCII file containing a binary image is available (see girl.img and img readme) as the input of your program. The output of the program should be thefollowing in this specified order:
1. the input binary image,
2. the connected component image where each component is labelled with a unique character,
3. a list sorted by component size, where each line of the list contains the size and the label of a component,
4. same as 2 with the connected components whose sizes are less than three deleted.

## Assignment3:
### Dijkstra.java
Implement the Dijkstra’s single source shortest path algorithm for a weighted directed graph using a heap data structure. The time complexity of the algorithm should be **O((|V |+ |E|) log |V |)**
A heap data structure for priority queue  should be implemented as an abstract data type (a class in C++, Java, or Python)on a set of elements, where each element has an id and a key, with the following operations.
##### heap(keys, n): initializes a heap with the array keys of n elements.
##### in heap(id): returns true if the element whose id is id is in the heap;
##### min key(): returns the minimum key of the heap;
##### min id(): returns the id of the element with minimum key in the heap;
##### key(id): returns the key of the element whose id is id in the heap;
##### delete min(): deletes the element with minimum key from the heap;
##### decrease key(id, new key): sets the key of the element whose id is id to new key if its current key is greater than new key.
An input graph file will be available. The format of the input file is the following:
1. The first line of the input file contains an integer, n, indicating the number of vertices of the input graph.
2. Each of the remaining lines contains a triple “i j w”, where 1 ≤ i, j ≤ n, indicating an edge from vertex i to vertex j with weight w.
##### Vertex 1 is considered as the source.
##### The output of your program should be the following:
1. The input graph in adjacency list representation: Print the adjacency lists in any reasonable format. Print each edge with its weight.
2. The shortest path tree edges with shortest path weights: The edges should be listed in the order in which they are produced by the Dijkastra’s algorithm. For each shortest path tree edge (i, j), print “(i, j) : w” in a separate line, where w is the shortest path weight from the source to vertex j.
