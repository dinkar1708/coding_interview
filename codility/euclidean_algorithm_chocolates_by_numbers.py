def solution(N, M):
    """
    DINAKAR
    for N-10 and M-4 ---- they will produce 5 as output it means 4*5 = 20 here they meets again to be eaten
    draw circle and jump 4 each time, you can find it easily

    N 10*2 = 20
    M 4*5 = 20
    above both has LCM  = 20 ie. at least common divisor there is the answer of the problem
    replcae 4 with M and 5 with unknown x
    M*x = 20 or LCM
    M*x = LCM
    M*x = LCM
    by LCM = {(N*M)/GCD}
    M*x = N*M/GCD
    cancel M both side
    x = N / GCD
    x = is the factor by when can eat all chocklates

    """
    # double-slash for “floor” division (rounds down to nearest whole number)
    # 4.5//2 = 2.0
    # 4.5/2 0 = 2.25
    return N // gcd(N, M)


def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)


if __name__ == '__main__':
    result = solution(10, 4)
    print("Solution " + str(result))
