def solution(S, P, Q):
    """
    https://app.codility.com/demo/results/training8QBVFJ-EQB/
    100%

    Idea is consider solution as single dimensional array and use concept of prefix some ie.
    stores the value in the array for p,c and g based on frequency
    array stores the frequency of p,c and g for all positions
    Example -
        # [0, 0, 1, 1, 1, 1, 1, 2] - prefix some of A - represents the max occurrence of A as 2 in array
        # [0, 1, 1, 1, 2, 3, 3, 3] - prefix some of C - represents the max occurrence of C as 3 in array
        # [0, 0, 0, 1, 1, 1, 1, 1] - prefix some of G - represents the max occurrence of G as 1 in array

    # To find the query answers we can just use prefix some and find the distance between position

    # two d array - column size is 3 for a,c,g - not taking size 4 since that will be part of else ie. don`t need to
     calculate. Row size is the length of DNA sequence
    S = CAGCCTA

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6

    :return:  return the values [2, 4, 1]
    """

    print(S)
    print(P)
    print(Q)

    prefix_sum_two_d_array = [[0 for i in range(len(S) + 1)] for j in range(3)]

    # find the prefix some of all nucleotide in given sequence
    for i, nucleotide in enumerate(S):
        print(i)
        print(nucleotide)
        # store prefix some of each
        # nucleotide == 'A -> 1 if true 0 if false
        # [0, 0, 1, 1, 1, 1, 1, 2] - prefix some of A - represents the max occurrence of A as 2 in array
        prefix_sum_two_d_array[0][i + 1] = prefix_sum_two_d_array[0][i] + (nucleotide == 'A')
        # store prefix some of c
        # [0, 1, 1, 1, 2, 3, 3, 3] - prefix some of C - represents the max occurrence of C as 3 in array
        prefix_sum_two_d_array[1][i + 1] = prefix_sum_two_d_array[1][i] + (nucleotide == 'C')
        # store prefix some of g
        # [0, 0, 0, 1, 1, 1, 1, 1] - prefix some of G - represents the max occurrence of G as 1 in array
        prefix_sum_two_d_array[2][i + 1] = prefix_sum_two_d_array[2][i] + (nucleotide == 'G')
        print("Prefix sum 2 d array for A, C, T")
        print(prefix_sum_two_d_array)
    print("Prefix sum 2 d array for A, C, T - max occurrence (A-2, C-3, T-1)")
    print(prefix_sum_two_d_array)
    print("Find the queries answer...")
    # now to find the query answers we can just use prefix some and find the distance between position
    query_answers = []
    for position in range(len(P)):
        # for each query of p
        # find the start index from p
        start_index = P[position]
        # find the end index from Q
        end_index = Q[position] + 1
        # find the value from prefix some array - just subtract end index and start index to find the value
        if prefix_sum_two_d_array[0][end_index] - prefix_sum_two_d_array[0][start_index]:
            print("Found for A ")
            query_answers.append(1)
        elif prefix_sum_two_d_array[1][end_index] - prefix_sum_two_d_array[1][start_index]:
            print("Found for C ")
            query_answers.append(2)
        elif prefix_sum_two_d_array[2][end_index] - prefix_sum_two_d_array[2][start_index]:
            print("Found for G ")
            query_answers.append(3)
        else:
            print("Found for T ")
            query_answers.append(4)
        print(start_index)
        print(end_index)
        print(query_answers)
    return query_answers


result = solution("CAGCCTA", [2, 5, 0], [4, 5, 6])
print("Sol " + str(result))
# Sol [2, 4, 1]
"""
CAGCCTA
[2, 5, 0]
[4, 5, 6]
0
C
Prefix sum 2 d array for A, C, T
[[0, 0, 0, 0, 0, 0, 0, 0], [0, 1, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0]]
1
A
Prefix sum 2 d array for A, C, T
[[0, 0, 1, 0, 0, 0, 0, 0], [0, 1, 1, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0]]
2
G
Prefix sum 2 d array for A, C, T
[[0, 0, 1, 1, 0, 0, 0, 0], [0, 1, 1, 1, 0, 0, 0, 0], [0, 0, 0, 1, 0, 0, 0, 0]]
3
C
Prefix sum 2 d array for A, C, T
[[0, 0, 1, 1, 1, 0, 0, 0], [0, 1, 1, 1, 2, 0, 0, 0], [0, 0, 0, 1, 1, 0, 0, 0]]
4
C
Prefix sum 2 d array for A, C, T
[[0, 0, 1, 1, 1, 1, 0, 0], [0, 1, 1, 1, 2, 3, 0, 0], [0, 0, 0, 1, 1, 1, 0, 0]]
5
T
Prefix sum 2 d array for A, C, T
[[0, 0, 1, 1, 1, 1, 1, 0], [0, 1, 1, 1, 2, 3, 3, 0], [0, 0, 0, 1, 1, 1, 1, 0]]
6
A
Prefix sum 2 d array for A, C, T
[[0, 0, 1, 1, 1, 1, 1, 2], [0, 1, 1, 1, 2, 3, 3, 3], [0, 0, 0, 1, 1, 1, 1, 1]]
Prefix sum 2 d array for A, C, T - max occurrence (A-2, C-3, T-1)
[[0, 0, 1, 1, 1, 1, 1, 2], [0, 1, 1, 1, 2, 3, 3, 3], [0, 0, 0, 1, 1, 1, 1, 1]]
Find the queries answer...
Found for C 
2
5
[2]
Found for T 
5
6
[2, 4]
Found for A 
0
7
[2, 4, 1]
Sol [2, 4, 1]

"""
