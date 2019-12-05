def solution(A, B, K):
    """
    https://app.codility.com/demo/results/training4G64ZF-DSG/
    100%
    
    // -> Division (floor): divides the first operand by the second

    given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K,

    credit of notes - https://codesays.com/2014/solution-to-count-div-by-codility/
    The size of the interval [A,B] determines the answer,
    however we distinguish the two different cases because whether or not K divides A affects the answer.
    Take K=10 for example. If A=20 and B=30 then the answer is 2. If A=21 and B=31
    then the answer is 1 despite the fact that the difference A-B here is the same.
    Since K divides A in the first instance the answer is one greater.
    Now, let a = A%K (i.e. A = a (mod K))
    so we may write A = a + mK for some integer m
    here mK is the largest multiple of K that is less than or equal to A
    and A – A%K = a + mK – a = mK
    (note if A is a multiple of K then a=0)
    the number of multiples of K in [A,B] is the same as the number of multiples of K in [mK,B] if a=0 (i.e. K divides M)
     and one less than this if a>0 (i.e. K doesn’t divide M), because in this case mK is not in the interval [A,B]
    Now, the number of multiples of K in [mK,B] is equal to the number of multiples of K in the interval [0,B-mK],
     which is floor((B-mK)/K) +1 (the plus one is there because 0 is a multiple)
    so the answer is
    (B-(A-A%K))//K if A%K>0
    and
    (B-(A-A%K))//K + 1 = (B-A)//K + 1 if A%K==0

    :return:
    """

    # a is divisible by k
    # use  // -> Division (floor): divides the first operand by the second example
    # (6%2) = 0

    if A % K == 0:
        print("divisible.....")
        return (B - A) // K + 1  # 11-6 // 2 = 5//2 = 2.5 = 3
    else:
        return (B - (A - A % K)) // K


result = solution(6, 11, 2)
print("Sol " + str(result))
