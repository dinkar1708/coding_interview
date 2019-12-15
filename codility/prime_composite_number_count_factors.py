def solution(N):
    """
    Problem Statement can be found here-
    https://app.codility.com/demo/results/trainingJNNRF6-VG4/
    Codility 100%

    Idea is count decedent factor in single travers. ie. if 24 is divisible by 4 then it is also divisible by 8
    Traverse only up to square root of number ie. in case of 24, 4*4 < 24 but 5*5!<24 so loop through only i*i<N
    """
    print(N)
    count = 0
    i = 1
    while i * i <= N:
        if N % i == 0:
            print()
            print("Divisible by " + str(i))
            if i * i == N:
                count += 1
                print("Count increase by one " + str(count))
            else:
                count += 2
                print("Also divisible by " + str(int(N / i)))
                print("Count increase by two count " + str(count))
        i += 1
    return count


if __name__ == '__main__':
    # result = solution(24)
    # result = solution(35)
    result = solution(1)
    print("")
    print("Solution " + str(result))

"""
Example1-
24

Divisible by 1
Also divisible by 24
Count increase by two count 2

Divisible by 2
Also divisible by 12
Count increase by two count 4

Divisible by 3
Also divisible by 8
Count increase by two count 6

Divisible by 4
Also divisible by 6
Count increase by two count 8

Solution 8


Example2-
35

Divisible by 1
Also divisible by 35
Count increase by two count 2

Divisible by 5
Also divisible by 7
Count increase by two count 4

Solution 4

Example3-

1

Divisible by 1
Count increase by one 1

Solution 1
"""
