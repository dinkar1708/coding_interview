def count(str):
    counter = [0]*26
    for ch in str:
        counter[ord(ch) - ord('a')] += 1
    return counter

# find the count of words from list which are made of same character
str = "az"
list = ["aza", "aaaa", "bbb", "ccc", "azaaaa", "ddd"]
# count should be 2 for "aza" and "azaaaa"

count_first_input = count(str)
print(count_first_input)
total_match = 0
for word in list:
    count_word = count(word)
    print(count_word)
    is_same = True
    for i in range(len(count_word)):
        if((count_first_input[i] ==0 and count_word[i]!= 0) or (count_word[i] ==0 and count_first_input[i]!= 0)):
            is_same = False
            print("break for....word, not matching... ", word)
            break
    if is_same:
        total_match += 1
print("Total match.....")
print(total_match)

"""
[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
[2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
[4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  aaaa
[0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  bbb
[0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  ccc
[5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1]
[0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
break for....word, not matching...  ddd
Total match.....
2
"""


        
