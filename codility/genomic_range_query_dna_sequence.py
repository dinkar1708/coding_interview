def solution(S, P, Q):
    """
    https://app.codility.com/demo/results/training8QBVFJ-EQB/
    100%

    Idea is consider solution as single dimensional array and use concept of prefix some ie.
    stores the value in array for p,c and g based on frequency
    array stores the frequency of p,c and g for all positions
    Example -
        # [0, 0, 1, 1, 1, 1, 1, 2] - prefix some of A - represents the max occurrence of A as 2 in array
        # [0, 1, 1, 1, 2, 3, 3, 3] - prefix some of C - represents the max occurrence of A as 3 in array
        # [0, 0, 0, 1, 1, 1, 1, 1] - prefix some of G - represents the max occurrence of A as 1 in array

    # To find the query answers we can just use prefix some and find the distance between position

    S = CAGCCTA

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6

    Given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q consisting
    of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

    The part of the DNA between positions 2 and 4 contains nucleotide G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
    The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
    The part between positions 0 and 6 (the whole string) contains all nucleotide, in particular nucleotide A whose impact factor is 1, so the answer is 1.

       N is an integer within the range [1..100,000];
       M is an integer within the range [1..50,000];
       each element of arrays P, Q is an integer within the range [0..N − 1];
       P[K] ≤ Q[K], where 0 ≤ K < M;
       string S consists only of upper-case English letters A, C, G, T.

    Ref - https://github.com/ghanan94/codility-lesson-solutions/blob/master/Lesson%2005%20-%20Prefix%20Sums/PrefixSums.pdf

    :return:  return the values [2, 4, 1]
       """
    # two d array - column size is 3 for a,c,g - not taking size 4 since that will be part of else ie. don`t need to calculate
    # row size is the length of DNA sequence
    prefix_sum_two_d_array = [[0 for i in range(len(S) + 1)] for j in range(3)]
    # find the prefix some of all nucleotide in given sequence
    for i, nucleotide in enumerate(S):
        # store prefix some of each
        # nucleotide == 'A -> 1 if true 0 if false
        # [0, 0, 1, 1, 1, 1, 1, 2] - prefix some of A - represents the max occurrence of A as 2 in array
        prefix_sum_two_d_array[0][i + 1] = prefix_sum_two_d_array[0][i] + (nucleotide == 'A')
        # store prefix some of c
        # [0, 1, 1, 1, 2, 3, 3, 3] - prefix some of C - represents the max occurrence of A as 3 in array
        prefix_sum_two_d_array[1][i + 1] = prefix_sum_two_d_array[1][i] + (nucleotide == 'C')
        # store prefix some of g
        # [0, 0, 0, 1, 1, 1, 1, 1] - prefix some of G - represents the max occurrence of A as 1 in array
        prefix_sum_two_d_array[2][i + 1] = prefix_sum_two_d_array[2][i] + (nucleotide == 'G')

    #print(prefix_sum_two_d_array)

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
            query_answers.append(1)
        elif prefix_sum_two_d_array[1][end_index] - prefix_sum_two_d_array[1][start_index]:
            query_answers.append(2)
        elif prefix_sum_two_d_array[2][end_index] - prefix_sum_two_d_array[2][start_index]:
            query_answers.append(3)
        else:
            query_answers.append(4)

    return query_answers


result = solution("CAGCCTA", [2, 5, 0], [4, 5, 6])
print("Sol " + str(result))
# Sol [2, 4, 1]
