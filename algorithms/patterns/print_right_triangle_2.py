def print_row(row, symbol):
	if (row == 0):
		return;
	print(symbol, end ="");
	print_row(row - 1, symbol);

def pattern(n, num):
	if (n == 0):
		return;
	# print space 
	print_row(n - 1, " ");
	# print star
	print_row(num - n + 1, "*");
	# print new line
	print();
	pattern(n - 1, num);

	
n = 6
pattern(n, n)

"""
     *
    **
   ***
  ****
 *****
******
"""