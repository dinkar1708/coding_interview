def print_row(num):
	if (num == 0):
		return
	print("*", end = " ")
	print_row(num - 1)

def print_pattern(n, i):	
	if (n == 0):
		return
    # print single line/row
	print_row(i)
    # print next line, start new line/row
	print()
	print_pattern(n - 1, i + 1)

n = 6
print_pattern(n, 1)

"""
* 
* * 
* * * 
* * * * 
* * * * * 
* * * * * * 
"""