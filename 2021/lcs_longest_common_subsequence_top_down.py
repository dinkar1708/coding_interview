def find_lcs(A, B, m, n):
    if m<0 or n<0:
        return 0
    if A[m-1] == B[n-1]:
        return 1 + find_lcs(A, B, m-1, n-1)
    else :
        return  max(find_lcs(A, B, m-1, n), find_lcs(A, B, m, n-1))

s1 = "ADC"
s2 = "ABCD"
print(find_lcs(list(s1), list(s2), len(s1) , len(s2)))