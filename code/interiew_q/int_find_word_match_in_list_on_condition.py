def count(str):
    counter = [0]*26
    for ch in str:
        counter[ord(ch) - ord('a')] += 1
    return counter


# find the count of words from list which are made of same character
str = "aza"
list = ["aza", "aaaa", "bbb", "ccc", "azaaaa", "ddd", "a"]
# count should be 2 for "aza" and "azaaaa"

count_first_input = count(str)
print(count_first_input)
total_match = 0
for word in list:
    count_word = count(word)
    print("Word count for ", word, count_word)
    is_same = True
    for i in range(len(count_word)):
        if((count_first_input[i] == 0 and count_word[i] != 0) or (count_word[i] == 0 and count_first_input[i] != 0)):
            is_same = False
            print("break for....word, not matching... ", word)
            break
    if is_same:
        total_match += 1
print("Total match.....")
print(total_match)

"""
[2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
Word count for  aza [2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
Word count for  aaaa [4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  aaaa
Word count for  bbb [0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  bbb
Word count for  ccc [0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  ccc
Word count for  azaaaa [5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
Word count for  ddd [0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  ddd
Word count for  a [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  a
Total match.....
2
"""
