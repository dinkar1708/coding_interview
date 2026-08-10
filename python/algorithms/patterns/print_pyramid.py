def print_row(row, symbol):
	if (row == 0):
		return;
	print(symbol, end ="");
	print_row(row - 1, symbol);

def pattern(n, num):
	if (n == 0):
		return;
	# print space in single line
	print_row(n - 1, " ");
	# print star in single line
	# start and space shift the symbole little right in each row, so they looks like in center
	print_row(num - n + 1, "* ");
	# print next line
	print();
	pattern(n - 1, num);

	
n = 6
pattern(n, n)

"""
     * 
    * * 
   * * * 
  * * * * 
 * * * * * 
* * * * * * 
"""