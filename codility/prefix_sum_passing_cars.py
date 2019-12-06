def solution(A):
    """
    https://app.codility.com/demo/results/trainingRNUS6P-6QQ/
    100%
    Idea-
    Before - we can just use two loops and for each zero count number of 1 in second loop big o n2

    But lets use prefix some to do it in linear time-
    As per question explanation we have to find the total pairs of ones with zeros towards east direction ie. right to left
    In order to find that we can use prefix some ie.
    prefix_sum = for any index it represents the total number of cars, which can not pass to any of its right side cars
    #               reason is only east moving car can pass to other cars as per problem statement

    So what we do is  - First find the # first_car_crosses_max - this is the first car which crosses all the cars to right/ moving to east of it
    Now for each car(zero/0) moving car we have to do first_car_crosses_max - prefix_sum[index] why we do see below-
    Why? The prefix sum for this index is the number of cars which are to the left side and for sure they will not pass current index car
    so we subtract all of these cars and we found the only cars which are moving to east direction passing all others cars. At this point we are
    100 % sure that all cars(1) must be passed by this car by definition of first_car_crosses_max

    See below- assume moving cars only in east direction, since given in question 0 ≤ P < Q < N so just assume that west car moving is still
    west<<<<<------>>>>>>>east
    0->>> travels east(passes 3 cars) 1 0->>> travels east(passes 2 cars) 1 1
    0 1 0 1 1
    for first zero do prefix some if current is 1 add with previous value
    0 1 1 2 3

    Problem-
    consider array A such that:
      A[0] = 0
      A[1] = 1
      A[2] = 0
      A[3] = 1
      A[4] = 1
    We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

    """

    print("Input      " + str(A))
    ar_length = len(A)
    # prefix_sum - for any index it represents the total number of cars which can not pass to any of its right side cars
    #               reason is only east moving car can pass to other cars as per problem statement
    prefix_sum = [0] * (ar_length + 1)
    for i in range(1, ar_length + 1):
        prefix_sum[i] = prefix_sum[i - 1] + A[i - 1]

    num_passing_cars = 0
    print("prefix_sum " + str(prefix_sum))
    # first_car_crosses_max - this is the first car which crosses all the cars to right of it
    first_car_crosses_max = prefix_sum[ar_length]
    print("first_car_crosses_max " + str(first_car_crosses_max))
    for index, car in enumerate(A):
        # 0 represents a car traveling east and we have to find the passing cars for zero so that we can find the total number of pairs/crossings
        if car == 0:
            print("")
            print("Can not pass cars " + str(prefix_sum[index]))
            print("Can pass car at current index " + str(first_car_crosses_max - prefix_sum[index]))
            # just subtract from max so we get the total cars passing at this point
            num_passing_cars += (first_car_crosses_max - prefix_sum[index])
            print("Index     " + str(index))
            print("Total num_passing_cars     " + str(num_passing_cars))
            # 10 ** 9 = 1000,000,000, exit for boundary condition as given in question
            if num_passing_cars > 10 ** 9:
                return -1
    return num_passing_cars

    # solution without prefix sum
    # https://app.codility.com/demo/results/trainingT9FN2S-BWK/"
    # zero_count = 0
    # count = 0
    # for i in A:
    #     if i == 0:
    #         # count the zero
    #         zero_count += 1
    #     else:
    #         count += zero_count * i
    #     if count > (10 ** 9):
    #         return -1
    # else:
    #     return count


result = solution([0, 1, 0, 1, 1])
print("")
print("Solution " + str(result))

"""
    Input      [0, 1, 0, 1, 1]
    prefix_sum [0, 0, 1, 1, 2, 3]
    first_car_crosses_max 3
    
    Can not pass cars 0
    Can pass car at current index 3
    Index     0
    Total num_passing_cars     3
    
    Can not pass cars 1
    Can pass car at current index 2
    Index     2
    Total num_passing_cars     5
    
    Solution 5
"""
