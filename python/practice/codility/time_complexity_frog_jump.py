import math


def solve(X, Y, D):
    """
    https://app.codility.com/demo/results/trainingP7B888-UFF/
    For example, given:

      X = 10
      Y = 85
      D = 30
    the function should return 3, because the frog will be positioned as follows:

    after the first jump, at position 10 + 30 = 40
    after the second jump, at position 10 + 30 + 30 = 70
    after the third jump, at position 10 + 30 + 30 + 30 = 100

    :return:
    """
    # total distance divide by jump
    # if fraction then take upper value example  2.5 = 3 - reason is after dividing some remaining part is there - 75/30 = 2.5
    return math.ceil((Y - X) / D)


result = solve(10, 85, 30)
print("Sol " + str(result))
