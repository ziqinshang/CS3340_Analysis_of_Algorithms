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
###### **uandf(n)**: constructs an disjoint-set data type with n elements, 1, 2, . . . , n.
###### **make_set(i)**: creates a new set whose only member (and thus representative) is i.
###### **union_sets(i, j)**: unites the dynamic sets that contains i and j, respectively, into a new set that is the union of these two sets.
###### **find_set(i)**: returns the representative of the set containing i.
###### **final_sets()**: returns the total number of current sets and finalizes the current sets:(i) make set() and union sets() will have no effect after this operation and (ii) resets the representatives
### question10.java
Design and implement (write a program) an algorithm to find the connected components in a binary image using Disjoint-Set data structure in a).An ASCII file containing a binary image is available (see girl.img and img readme) as the input of your program. The output of the program should be thefollowing in this specified order:
1. the input binary image,
2. the connected component image where each component is labelled with a unique character,
3. a list sorted by component size, where each line of the list contains the size and the label of a component,
4. same as 2 with the connected components whose sizes are less than three deleted.
