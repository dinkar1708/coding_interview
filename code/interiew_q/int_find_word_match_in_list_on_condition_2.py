def make_dictionary(str):
    """
   Make dictionary by putting the keys and 1 as value
    """
    counter = {}
    for ch in str:
        counter[ch] = 1
    return counter


def check_keys(first_dict_key, second_dict_key):
    """
    Check for both keys present in each dictionary
    """
    print("All count present...", first_dict_key, second_dict_key)
    return set(first_dict_key) == set(second_dict_key)


def solve(str, list):
    """
    Idea is use python dict , check for all keys to be equal for each word in list
    """
    count_first_input = make_dictionary(str).keys()
    print(count_first_input)
    total_match = 0
    for word in list:
        count_word = make_dictionary(word).keys()
        # print("Word count for ",word, count_word)
        if check_keys(count_first_input, count_word):
            print("Match...")
            total_match += 1
    print("Total match.....")
    print(total_match)


# find the count of words from list which are made of same character
str = "aza"
list = ["aza", "aaaa", "bbb", "ccc", "azaaaa", "ddd"]
# count should be 2 for "aza" and "azaaaa"
solve(str, list)


"""
dict_keys(['a', 'z'])
All count present... dict_keys(['a', 'z']) dict_keys(['a', 'z'])
Match...
All count present... dict_keys(['a', 'z']) dict_keys(['a'])
All count present... dict_keys(['a', 'z']) dict_keys(['b'])
All count present... dict_keys(['a', 'z']) dict_keys(['c'])
All count present... dict_keys(['a', 'z']) dict_keys(['a', 'z'])
Match...
All count present... dict_keys(['a', 'z']) dict_keys(['d'])
Total match.....
2
"""
