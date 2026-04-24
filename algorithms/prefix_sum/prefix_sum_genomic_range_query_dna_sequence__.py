# https://app.codility.com/demo/results/trainingHUDAYQ-3DJ/
def solution(S, P, Q):
    """
    DINAKAR

    Order A,C,G,T => order as impact factor 1,2,3,4
    in loop A,C,G,T appends 1,2,3,4 due to order and value of impact factor

    Objective is to find the minimal impact in each P,Q

    Clue- below line has the hidden answer - [find the minimal impact factor of nucleotides contained in the DNA
    sequence between positions P[K] and Q[K]
     (inclusive).]
    - we know for each slice if we check only availability of characters in the order given above so it will always
    give the minimal impact.
    eg.
    for first slice  P=> 2 Q=> 5
    GCC
    I just need to check which A,C,G or T is available first
    A - no
    C - yes -> for this slice minimal impact is 2

    """

    print(S)
    print(P)
    print(Q)

    query_answer = []
    for i in range(len(P)):
        print()
        # ar[start:end] = produce the slice ie. part of array / sub set of array
        slice_ = S[P[i]:Q[i] + 1]
        print("Slice...for position " + str(i) + ", P=> " + str(P[i]) + " Q=> " + str(Q[i] + 1))
        print(slice_)
        if "A" in slice_:
            print("A is in slice...")
            query_answer.append(1)
        elif "C" in slice_:
            print("C is in slice...")
            query_answer.append(2)
        elif "G" in slice_:
            print("G is in slice...")
            query_answer.append(3)
        elif "T" in slice_:
            print("T is in slice...")
            query_answer.append(4)
        print("query_answer " + str(query_answer))
    return query_answer


result = solution("CAGCCTA", [2, 5, 0], [4, 5, 6])
print("Sol " + str(result))
"""
CAGCCTA
[2, 5, 0]
[4, 5, 6]

Slice...for position 0, P=> 2 Q=> 5
GCC
C is in slice...
query_answer [2]

Slice...for position 1, P=> 5 Q=> 6
T
T is in slice...
query_answer [2, 4]

Slice...for position 2, P=> 0 Q=> 7
CAGCCTA
A is in slice...
query_answer [2, 4, 1]
Sol [2, 4, 1]
"""
